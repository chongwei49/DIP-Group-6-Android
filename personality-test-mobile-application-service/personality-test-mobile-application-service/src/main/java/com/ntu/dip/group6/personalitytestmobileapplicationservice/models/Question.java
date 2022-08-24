package com.ntu.dip.group6.personalitytestmobileapplicationservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
public class Question {

    @Id
    private String questionID;

    private String questionCategory;
    private String question;
    private String answer;
    private String traits;

    public Question() {

    }

    public Question (String questionID, String questionCategory, String question, String answer, String traits) {
        this.questionID = questionID;
        this.questionCategory = questionCategory;
        this.question = question;
        this.answer = answer;
        this.traits = traits;
    }

    public String getQuestionID() {
        return this.questionID;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }

    public String getQuestionCategory() {
        return this.questionCategory;
    }

    public void setQuestionCategory(String questionCategory) {
        this.questionCategory = questionCategory;
    }

    public String getQuestion() {
        return this.question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return this.answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getTraits() {
        return this.traits;
    }

    public void setTraits(String traits) {
        this.traits = traits;
    }

}
