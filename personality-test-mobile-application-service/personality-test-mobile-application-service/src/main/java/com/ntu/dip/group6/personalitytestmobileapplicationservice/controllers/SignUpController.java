package com.ntu.dip.group6.personalitytestmobileapplicationservice.controllers;

import com.ntu.dip.group6.personalitytestmobileapplicationservice.models.User;
import com.ntu.dip.group6.personalitytestmobileapplicationservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/signup")
public class SignUpController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<String> signup(@RequestBody User user) {
        if (userService.addNewUser(user) == "Success") {
            return new ResponseEntity<>("User has been successfully added.", HttpStatus.OK);
        } else if (userService.addNewUser(user) == "Email and username duplicate") {
            return new ResponseEntity<>("Email and username already exist.", HttpStatus.BAD_REQUEST);
        } else if (userService.addNewUser(user) == "Username duplicates") {
            return new ResponseEntity<>("Username already exists.", HttpStatus.BAD_REQUEST);
        } else if (userService.addNewUser(user) == "Email duplicates") {
            return new ResponseEntity<>("Email already exists.", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}