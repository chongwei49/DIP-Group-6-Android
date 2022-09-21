package com.ntu.dip.group6.personalitytestmobileapplicationservice.controllers;

import com.ntu.dip.group6.personalitytestmobileapplicationservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/login")
public class LoginController {

    @Autowired
    UserService userService;
    @GetMapping
    public ResponseEntity<Object> login(@RequestHeader("Authorization") String authHeader) {

        Boolean validated = userService.loginValidator(authHeader);
        if (validated)
            return new ResponseEntity<>("Login Successful", HttpStatus.OK);
        else
            return new ResponseEntity<>("Unauthorized", HttpStatus.BAD_REQUEST);
    }


}
