package com.ntu.dip.group6.personalitytestmobileapplicationservice.controllers;

import com.nimbusds.oauth2.sdk.util.StringUtils;
import com.ntu.dip.group6.personalitytestmobileapplicationservice.models.User;
import com.ntu.dip.group6.personalitytestmobileapplicationservice.services.UserService;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/signup")
@CrossOrigin(origins = "*")
public class SignUpController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<Object> signup(@RequestBody User user) {
        String response = userService.addNewUser(user);
        if (response == "Email duplicates") {
            return new ResponseEntity<>("Email already exists.", HttpStatus.BAD_REQUEST);
        } else if (StringUtils.isNumeric(response)) {
            Integer userId = Integer.parseInt(response);
            return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}