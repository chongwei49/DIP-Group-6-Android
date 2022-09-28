package com.ntu.dip.group6.personalitytestmobileapplicationservice.controllers;

import com.ntu.dip.group6.personalitytestmobileapplicationservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/login")
@CrossOrigin(origins = "*")
public class LoginController {

    @Autowired
    UserService userService;
    @GetMapping
    public ResponseEntity<Object> login(@RequestHeader("Authorization") String authHeader) {

        String validated = userService.loginValidator(authHeader);
        if (validated == "Authorized")
            return new ResponseEntity<>("Login Successful", HttpStatus.OK);
        else
            return new ResponseEntity<>(validated, HttpStatus.BAD_REQUEST);
    }

}
