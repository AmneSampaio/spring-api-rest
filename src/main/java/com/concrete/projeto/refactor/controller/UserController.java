package com.concrete.projeto.refactor.controller;

import com.concrete.projeto.refactor.model.JwtRequest;
import com.concrete.projeto.refactor.model.JwtResponse;
import com.concrete.projeto.refactor.model.Phone;
import com.concrete.projeto.refactor.model.User;
import com.concrete.projeto.refactor.repository.UserRepository;
import com.concrete.projeto.refactor.service.UserService;
import com.concrete.projeto.refactor.utility.JWTUtility;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> findById(@PathVariable("id") UUID id) {
        Optional<User> userDB = userRepository.findById(id);

        if (!userDB.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(userDB.get());
    }

    @PostMapping("/signIn")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@Valid @RequestBody User user) throws Exception {
        Optional<User> userExistsByEmail = userRepository.findByEmail(user.getEmail());

        if (userExistsByEmail.isPresent()) {
            return ResponseEntity.badRequest().build();
        } else {
            //user.cryptPassword();
            return ResponseEntity.ok(userRepository.save(user));
        }

    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> login(@Valid @RequestBody User user) {
        Optional<User> lookForEmailOnDB = userRepository.findByEmail(user.getEmail());

        if (lookForEmailOnDB.isPresent() && lookForEmailOnDB.get().getEmail().equals(user.getEmail())) {

            /*String lookForPasswordCrypt = passwordEncoder().encode(user.getPassword());
            if (lookForEmailOnDB.get().getPassword().equals(lookForPasswordCrypt)) {*/

            lookForEmailOnDB.get().setLastUserLogin(LocalDateTime.now());
            userRepository.save(lookForEmailOnDB.get());
        }


        return new ResponseEntity<User>(lookForEmailOnDB.get(),HttpStatus.OK);

    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
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
    public ResponseEntity<?> remove(@PathVariable Long id) {
        Optional<User> userID = userRepository.findById(id);
        if (!userID.isPresent()) { return ResponseEntity.notFound().build(); }

        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();

    }
/*

    private boolean validateSignIn(String email) throws Exception {
        if (userRepository.existsByEmail(email)) {
            throw new Exception("Email already in use.");
        }
        return true;
    }
*/

}
