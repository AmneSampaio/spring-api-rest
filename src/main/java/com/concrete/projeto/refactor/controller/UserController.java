package com.concrete.projeto.refactor.controller;

import com.concrete.projeto.refactor.model.Phone;
import com.concrete.projeto.refactor.model.User;
import com.concrete.projeto.refactor.repository.UserRepository;
import com.concrete.projeto.refactor.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        if (!userDB.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(userDB.get());
    }

    /*@GetMapping("/email:{email}")
    public User findByEmail(@PathVariable("email") String email) {
        return userRepository.findByEmail(email).get();
    }*/

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody User user) {
       return ResponseEntity.ok().body(userRepository.save(user));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) {

        Optional<User> userFoundById = userRepository.findById(id);

        if (!userFoundById.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        // TODO pesquisar em todos os email do banco de dados e verificar se o email de user já existe
        /*if (userFoundById.get().getName().equals(user.getName()) ||
            userFoundById.get().getEmail().equals(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();

        }*/
        userFoundById.get().setEmail(user.getEmail());
        userFoundById.get().setName(user.getName());

        /*for (Address currentAddress : userFoundById.get().getAddress()) {
            if (currentAddress.getCep().equals(user.getAddress().get(0).getCep()) &&
                    currentAddress.getNumber().equals(user.getAddress().get(0).getNumber())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
        }*/

        List<Address> userAllAddresses = userFoundById.get().getAddress();
        userAllAddresses.add(user.getAddress().get(0));
        userFoundById.get().setAddress(userAllAddresses);

        /*for (Phone currentPhone : userFoundById.get().getPhone()) {
            if (currentPhone.getNumber().equals(user.getPhone().get(0).getNumber()))//&&
                    *//*currentPhone.getDdd().equals(user.getPhone().get(0).getDdd()))*//* {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();

            }
        }*/

        List<Phone> userAllPhones = userFoundById.get().getPhone();
        userAllPhones.add(user.getPhone().get(0));
        userFoundById.get().setPhone(userAllPhones);

        return ResponseEntity.ok().body(userRepository.save(userFoundById.get()));

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> remove(@PathVariable Long id) {
        Optional<User> userID = userRepository.findById(id);
        if (!userID.isPresent()) { return ResponseEntity.notFound().build(); }

        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();

    }
}
