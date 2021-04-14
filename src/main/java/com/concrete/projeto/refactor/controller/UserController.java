package com.concrete.projeto.refactor.controller;

import com.concrete.projeto.refactor.api.object.ViaCepObject;
import com.concrete.projeto.refactor.api.object.returnMessage;
import com.concrete.projeto.refactor.model.Phone;
import com.concrete.projeto.refactor.model.User;
import com.concrete.projeto.refactor.repository.UserRepository;
import com.concrete.projeto.refactor.model.Address;
import com.concrete.projeto.refactor.service.RestTemplateService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> findById(@PathVariable("id") Long id) {
        Optional<User> userDB = userRepository.findById(id);

        if (!userDB.isPresent()) { return ResponseEntity.notFound().build(); }

        return ResponseEntity.ok(userDB.get());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses( value = {
            @ApiResponse(code = 201, message = "Successfully"),
            @ApiResponse(code = 400, message = "An error has occurred creating the user")
    })
    public ResponseEntity<?> create(@RequestBody User user) {

        ViaCepObject viaCepObject = RestTemplateService.getCepViaRestTemplate(user.getAddress().getCep());

        if (viaCepObject == null || userRepository.existsByCpfOrLogin(user.getCpf(), user.getLogin())) {

            return ResponseEntity.status(400).body(new returnMessage("Invalid CEP or CPF/Login already signed in"));
        } else {
            user.getAddress().atualizarComViaCepObject(viaCepObject);
            // user.criptografarSenha();

            return ResponseEntity.ok().body(userRepository.save(user));
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @ApiResponses( value = {
            @ApiResponse(code = 201, message = "Successfully"),
            @ApiResponse(code = 400, message = "An error has occurred updating the user")
    })
    public ResponseEntity<User> update(@PathVariable("id") Long id,@RequestBody User user) {

        Optional<User> userFoundById = userRepository.findById(id);

        if (!userFoundById.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        userFoundById.get().setEmail(user.getEmail());
        userFoundById.get().setName(user.getName());

        List<Address> userAllAddresses = userFoundById.get().getAddress();
        userAllAddresses.add(user.getAddress().get(0));
        userFoundById.get().setAddress(userAllAddresses);

        List<Phone> userAllPhones = userFoundById.get().getPhone();
        userAllPhones.add(user.getPhone().get(0));
        userFoundById.get().setPhone(userAllPhones);

        return ResponseEntity.ok().body(userRepository.save(userFoundById.get()));

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully removed"),
            @ApiResponse(code = 404, message = "Register not found")
    })
    public ResponseEntity<?> remove(@PathVariable Long id) {
        Optional<User> userID = userRepository.findById(id);
        if (!userID.isPresent()) { return ResponseEntity.notFound().build(); }

        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();

    }

    private boolean validateSignIn(String cpf, String login) throws Exception {
        if (userRepository.existsByCpfOrLogin(cpf, login)) {
            throw new Exception("Cpf already present in the database");
        }
        return true;
    }

}
