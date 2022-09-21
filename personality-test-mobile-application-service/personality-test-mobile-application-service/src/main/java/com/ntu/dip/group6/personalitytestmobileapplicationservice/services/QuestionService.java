package com.ntu.dip.group6.personalitytestmobileapplicationservice.services;

import com.ntu.dip.group6.personalitytestmobileapplicationservice.models.Question;
import com.ntu.dip.group6.personalitytestmobileapplicationservice.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    public Iterable<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public void addNewQuestion(Question question) {
        Question newQuestion = new Question(question.getQnId(), question.getQnCategory(), question.getQns(), question.getAnswer(), question.getTraits());
        questionRepository.save(newQuestion);
    }

    public void editQuestion(Integer questionID, Question updatedQuestion) {
        Question question = questionRepository.findById(questionID).get();
        question.setQnCategory(updatedQuestion.getQnCategory());
        question.setQns(updatedQuestion.getQns());
        question.setAnswer(updatedQuestion.getAnswer());
        question.setTraits(updatedQuestion.getTraits());

        questionRepository.save(question);
    }

    public void deleteQuestion(Integer questionID) {
        questionRepository.deleteById(questionID);
    }
}
