package com.api.tp.controllers;

import com.api.tp.dto.UserEmailPwd;
import com.api.tp.models.User;
import com.api.tp.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping(value = "")
    List<User> all() {
        return userRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    User getOne(@PathVariable(name = "id", required = false) User user) {
        if (user == null) {
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT, "User not found"
            );
        }
        return user;
    }

    @PostMapping(value = "/search")
     User getOneByEmailAndPassword(@RequestBody UserEmailPwd userData) {
        User user = userRepository.findByEmail(userData.getEmail());

//       List<User> user = userRepository.findByEmailAndPassword(email, password);
        if (user == null) {
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT, "User not found"
            );
        }
        String userPassword = user.getPassword();
        Boolean passwordIsMatching = passwordEncoder.matches(userData.getPassword(), userPassword);

        if(!passwordIsMatching) {
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT, "User not found"
            );
        }
            return user;
    }

    @PostMapping(value = "/") // http://localhost:8090/users/
    //    @Valid to validate the data format
    public ResponseEntity<User> saveUser(@Valid @RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<User> update(@PathVariable(name = "id",required = false) User user, @Valid @RequestBody User userUpdate, BindingResult bindingResult) {

        if (user == null) {
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT, "User not found"
            );
        } else {
            if (bindingResult.hasErrors()) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, bindingResult.toString()
                );
            } else {
                userUpdate.setPassword(passwordEncoder.encode(userUpdate.getPassword()));
                userUpdate.setId(user.getId());
                userRepository.save(userUpdate);
                return new ResponseEntity<>(userUpdate, HttpStatus.OK);
            }
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(name = "id",required = false) User user) {
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user introuvable" );
        }
        userRepository.delete(user);
        return ResponseEntity.ok("{message: deleted_ok}");
    }
}
