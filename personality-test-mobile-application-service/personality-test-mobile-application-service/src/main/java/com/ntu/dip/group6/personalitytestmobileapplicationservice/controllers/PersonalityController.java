package com.ntu.dip.group6.personalitytestmobileapplicationservice.controllers;

import com.ntu.dip.group6.personalitytestmobileapplicationservice.models.Personality;
import com.ntu.dip.group6.personalitytestmobileapplicationservice.services.PersonalityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<String> addNewPersonality(@RequestBody Personality personality) {
        personalityService.addNewPersonality(personality);
        return new ResponseEntity<>("Personality has been successfully added.", HttpStatus.OK);
    }

    @PutMapping("/{priId}")
    public ResponseEntity<String> editPersonality(@PathVariable Integer priId, @RequestBody Personality personality) {
        personalityService.editPersonality(priId, personality);
        return new ResponseEntity<>("Personality has been succesfully edited.", HttpStatus.OK);
    }

    @DeleteMapping("/{priId}")
    public ResponseEntity<String> deletePersonality(@PathVariable Integer priId) {
        personalityService.deletePersonality(priId);
        return new ResponseEntity<>("Personality has been successfully deleted.", HttpStatus.OK);
    }

}

