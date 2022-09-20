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
        if (userService.addNewUser(user)) {
            return new ResponseEntity<>("User has been successfully added.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Username already exists.", HttpStatus.BAD_REQUEST);
        }

    }
}