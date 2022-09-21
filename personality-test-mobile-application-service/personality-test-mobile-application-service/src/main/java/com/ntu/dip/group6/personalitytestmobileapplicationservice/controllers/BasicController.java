package com.ntu.dip.group6.personalitytestmobileapplicationservice.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicController {

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/restricted")
    public String restricted(){
        return "restricted";
    }

    @GetMapping("/user")
    public String user(Model model,
                       @AuthenticationPrincipal OidcUser oidcUser) {
        model.addAttribute("userName", oidcUser.getName());
        model.addAttribute("audience", oidcUser.getAudience());
        return "user";
    }

}
