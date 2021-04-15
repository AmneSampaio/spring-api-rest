package com.concrete.projeto.refactor.controller;

import com.concrete.projeto.refactor.model.Phone;
import com.concrete.projeto.refactor.model.User;
import com.concrete.projeto.refactor.repository.UserRepository;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    };

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
            user.cryptPassword();
        return ResponseEntity.ok().body(userRepository.save(user));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses( value = {
            @ApiResponse(code = 201, message = "Successfully"),
            @ApiResponse(code = 400, message = "An error has occurred creating the user")
    })
    public ResponseEntity<?> login(@Valid @NotBlank @NotNull @RequestBody User user) {
        Optional<User> lookForEmailOnDB = userRepository.findByEmail(user.getEmail());

        if (lookForEmailOnDB.isPresent()) {

            String lookForPasswordCrypt = passwordEncoder().encode(user.getPassword());
            if (lookForEmailOnDB.get().getEmail().equals(user.getEmail())) {
                lookForEmailOnDB.get().s
            }
        return ResponseEntity.ok().body(userRepository.save(user));
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

    private boolean validateSignIn(String email, String login) throws Exception {
        if (userRepository.existsByEmailOrLogin(email, login)) {
            throw new Exception("Email already in use.");
        }
        return true;
    }

}
