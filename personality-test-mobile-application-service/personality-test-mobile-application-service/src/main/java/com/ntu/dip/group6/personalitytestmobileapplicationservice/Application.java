package com.ntu.dip.group6.personalitytestmobileapplicationservice;

import com.ntu.dip.group6.personalitytestmobileapplicationservice.models.User;
import com.ntu.dip.group6.personalitytestmobileapplicationservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
public class Application  {

	@Autowired

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
