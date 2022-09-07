package com.ntu.dip.group6.personalitytestmobileapplicationservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer priId;
    private Integer qnId;
    private String qnCategory;
    private String qns;
    private boolean answer;
    private String traits;

    public Question() {

    }
    public Question (Integer qnId, String qnCategory, String qns, boolean answer, String traits) {
        this.qnId = qnId;
        this.qnCategory = qnCategory;
        this.qns = qns;
        this.answer = answer;
        this.traits = traits;
    }

    public Integer getQnId() {
        return this.qnId;
    }

    public void setQnId(Integer qnId) {
        this.qnId = qnId;
    }

    public String getQnCategory() {
        return this.qnCategory;
    }

    public void setQnCategory(String questionCategory) {
        this.qnCategory = qnCategory;
    }

    public String getQns() {
        return this.qns;
    }

    public void setQns(String question) {
        this.qns = qns;
    }

    public boolean getAnswer() { return this.answer; }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    public String getTraits() {
        return this.traits;
    }

    public void setTraits(String traits) {
        this.traits = traits;
    }

}
