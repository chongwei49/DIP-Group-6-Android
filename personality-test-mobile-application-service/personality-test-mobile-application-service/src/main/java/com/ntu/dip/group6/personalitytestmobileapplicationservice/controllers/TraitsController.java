package com.ntu.dip.group6.personalitytestmobileapplicationservice.controllers;

import com.ntu.dip.group6.personalitytestmobileapplicationservice.models.Question;
import com.ntu.dip.group6.personalitytestmobileapplicationservice.models.Traits;
import com.ntu.dip.group6.personalitytestmobileapplicationservice.services.TraitsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v2/traits")
@CrossOrigin(origins = "*")
public class TraitsController {

    @Autowired
    TraitsService traitsService;

    @GetMapping
    public ResponseEntity<Object> getAllTraits() {
        return new ResponseEntity<>(traitsService.getAllTraits(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addNewTrait(@RequestBody Traits trait) {
        traitsService.addNewTrait(trait);
        return new ResponseEntity<>("Trait has been successfully added.", HttpStatus.OK);
    }

    @PutMapping("/{traitID}")
    public ResponseEntity<String> editTrait(@PathVariable Integer priID, @RequestBody Traits updatedTrait) {
        traitsService.editTrait(priID, updatedTrait);
        return new ResponseEntity<>("Trait has been successfully updated.", HttpStatus.OK);
    }

    @DeleteMapping("/{questionID}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Integer priID) {
        traitsService.deleteTrait(priID);
        return new ResponseEntity<>("Trait has been successfully deleted.", HttpStatus.OK);
    }

}
