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
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<Object> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{userID}")
    public ResponseEntity<Object> getUserById(@PathVariable Integer userID) {
        if (userService.getAllUsers() != null)
            return new ResponseEntity<>(userService.getUserById(userID), HttpStatus.OK);
        else
            return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
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
