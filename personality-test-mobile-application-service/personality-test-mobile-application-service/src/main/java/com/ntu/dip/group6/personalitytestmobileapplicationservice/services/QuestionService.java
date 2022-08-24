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
        UUID uuid = UUID.randomUUID();
        String questionID = uuid.toString();
        Question newQuestion = new Question(questionID, question.getQuestionCategory(), question.getQuestion(), question.getAnswer(), question.getTraits());
        questionRepository.save(newQuestion);
    }

    public void editQuestion(String questionID, Question updatedQuestion) {
        Question question = questionRepository.findById(questionID).get();
        question.setQuestionCategory(updatedQuestion.getQuestionCategory());
        question.setQuestion(updatedQuestion.getQuestion());
        question.setAnswer(updatedQuestion.getAnswer());
        question.setTraits(updatedQuestion.getTraits());

        questionRepository.save(question);
    }

    public void deleteQuestion(String questionID) {
        questionRepository.deleteById(questionID);
    }
}
