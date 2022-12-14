package com.ntu.dip.group6.personalitytestmobileapplicationservice.controllers;

import com.ntu.dip.group6.personalitytestmobileapplicationservice.models.Personality;
import com.ntu.dip.group6.personalitytestmobileapplicationservice.models.User;
import com.ntu.dip.group6.personalitytestmobileapplicationservice.services.PersonalityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@RestController
@RequestMapping("/api/v2/personalities")
@CrossOrigin(origins = "*")
public class PersonalityController {

    @Autowired
    PersonalityService personalityService;

    @GetMapping
    public ResponseEntity<Object> getAllPersonalities() {
        return new ResponseEntity<>(personalityService.getAllPersonalities(), HttpStatus.OK);
    }

    @GetMapping("/{priId}")
    public ResponseEntity<Object> getPersonalityById(@PathVariable Integer priId) {
        return new ResponseEntity<>(personalityService.getPersonalityById(priId), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Object> getPersonalityByUserId(@PathVariable Integer userId) {
        return new ResponseEntity<>(personalityService.getPersonalityByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("user/latest/{qnCategory}/{userId}")
public ResponseEntity<Object> getPersonalityByUserIdLatest(@PathVariable String qnCategory, @PathVariable Integer userId) {
        return new ResponseEntity<>(personalityService.getPersonalityByUserIdLatest(qnCategory, userId), HttpStatus.OK);
    }

    @GetMapping("/check/{userId}")
    public ResponseEntity<Object> checkTestDone(@PathVariable Integer userId) {
        return new ResponseEntity<>(personalityService.checkTestDone(userId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> addNewPersonality(@RequestBody Personality personality) {
        return new ResponseEntity<>(personalityService.addNewPersonality(personality), HttpStatus.OK);
    }

    @PutMapping("/{priId}")
    public ResponseEntity<Object> editPersonality(@PathVariable Integer priId, @RequestBody Personality personality) {
        return new ResponseEntity<>(personalityService.editPersonality(priId, personality), HttpStatus.OK);
    }

    @DeleteMapping("/{priId}")
    public ResponseEntity<String> deletePersonality(@PathVariable Integer priId) {
        personalityService.deletePersonality(priId);
        return new ResponseEntity<>("Personality has been successfully deleted.", HttpStatus.OK);
    }

    @GetMapping("/friends/{qnCategory}/{personalityType}")
    public ResponseEntity<List<User>> findCompatibilities(@PathVariable String qnCategory, @PathVariable String personalityType) {
        return new ResponseEntity<>(personalityService.getCompatibility(qnCategory, personalityType), HttpStatus.OK);
    }

    @GetMapping("/latest/{qnCategory}")
    public ResponseEntity<List<Personality>> findLatestPersonalities(@PathVariable String qnCategory) {
        return new ResponseEntity<>(personalityService.getPersonalityListLatest(qnCategory), HttpStatus.OK);
    }

}

