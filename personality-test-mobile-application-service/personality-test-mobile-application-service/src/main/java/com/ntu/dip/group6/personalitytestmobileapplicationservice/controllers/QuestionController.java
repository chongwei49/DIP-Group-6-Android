package com.ntu.dip.group6.personalitytestmobileapplicationservice.controllers;

import com.ntu.dip.group6.personalitytestmobileapplicationservice.models.Question;
import com.ntu.dip.group6.personalitytestmobileapplicationservice.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/questions")
@CrossOrigin(origins = "*")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping
    public ResponseEntity<Object> getAllQuestions() {
        return new ResponseEntity<>(questionService.getAllQuestions(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addNewQuestion(@RequestBody Question question) {
        questionService.addNewQuestion(question);
        return new ResponseEntity<>("Question has been successfully added.", HttpStatus.OK);
    }

    @PutMapping("/{questionID}")
    public ResponseEntity<String> editQuestion(@PathVariable Integer questionID, @RequestBody Question updatedQuestion) {
        questionService.editQuestion(questionID, updatedQuestion);
        return new ResponseEntity<>("Question has been successfully updated.", HttpStatus.OK);
    }

    @DeleteMapping("/{questionID}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Integer questionID) {
        questionService.deleteQuestion(questionID);
        return new ResponseEntity<>("Question has been successfully deleted.", HttpStatus.OK);
    }

}
