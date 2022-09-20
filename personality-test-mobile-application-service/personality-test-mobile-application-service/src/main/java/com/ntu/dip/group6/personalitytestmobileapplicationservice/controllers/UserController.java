package com.ntu.dip.group6.personalitytestmobileapplicationservice.controllers;

import com.ntu.dip.group6.personalitytestmobileapplicationservice.models.User;
import com.ntu.dip.group6.personalitytestmobileapplicationservice.repositories.UserRepository;
import com.ntu.dip.group6.personalitytestmobileapplicationservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v2/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<Object> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addNewUser(@RequestBody User user) {
        if (userService.addNewUser(user)) {
            return new ResponseEntity<>("User has been successfully added.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Username already exists.", HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/{userID}")
    public ResponseEntity<String> updateUser(@PathVariable Integer userID, @RequestBody User updatedUser) {
        userService.editUser(userID, updatedUser);
        return new ResponseEntity<>("User has been successfully updated.", HttpStatus.OK);
    }

    @DeleteMapping("/{userID}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer userID) {
        userService.deleteUser(userID);
        return new ResponseEntity<>("User has been successfully deleted.", HttpStatus.OK);
    }

}
