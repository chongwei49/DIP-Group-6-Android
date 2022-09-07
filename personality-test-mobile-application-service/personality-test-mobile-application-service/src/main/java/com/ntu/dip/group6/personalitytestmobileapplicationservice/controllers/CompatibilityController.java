package com.ntu.dip.group6.personalitytestmobileapplicationservice.controllers;

import com.ntu.dip.group6.personalitytestmobileapplicationservice.services.CompatibilityService;
import com.ntu.dip.group6.personalitytestmobileapplicationservice.models.Compatibility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/compatibilities")
public class CompatibilityController {

    @Autowired
    CompatibilityService compatibilityService;

    @GetMapping("/all")
    public ResponseEntity<Object> getAllCompatibilities() {
        return new ResponseEntity<>(compatibilityService.getAllCompatibilities(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addCompatibility(@RequestBody Compatibility compatibility) {
        compatibilityService.addNewCompatibility(compatibility);
        return new ResponseEntity<>("Compatibility has been successfully added.", HttpStatus.OK);
    }

    @PutMapping("/{priId}")
    public ResponseEntity<String> editCompatibility(@PathVariable Integer priId, @RequestBody Compatibility compatibility) {
        compatibilityService.editCompatibility(priId, compatibility);
        return new ResponseEntity<>("Compatibility has been successfullhy added.", HttpStatus.OK);
    }

    @DeleteMapping("/{priId}")
    public ResponseEntity<String> deleteCompatibility(@PathVariable Integer priId) {
        compatibilityService.deleteCompatibility(priId);
        return new ResponseEntity<>("Compatibiliy has been successfully deleted.", HttpStatus.OK);
    }



}
