package com.example.personalitytest.models;

public class Question {
    public int priId;
    public int qnId;
    public String qnCategory;
    public String qns;
    public Boolean answer;
    public String traits;

    public Question() {}

    public Question(int priId, int qnId, String qnCategory, String qns, Boolean answer, String traits) {
        this.priId = priId;
        this.qnId = qnId;
        this.qnCategory = qnCategory;
        this.qns = qns;
        this.answer = answer;
        this.traits = traits;
    }

    public Integer getPriId() {
        return this.priId;
    }

    public void setPriId(Integer priId) {
        this.priId = priId;
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

    public void setQnCategory(String qnCategory) {
        this.qnCategory = qnCategory;
    }

    public String getQns() {
        return this.qns;
    }

    public void setQns(String qns) {
        this.qns = qns;
    }

    public Boolean getAnswer() {
        return this.answer;
    }

    public void setAnswer(Boolean answer) {
        this.answer = answer;
    }

    public String getTraits() {
        return this.traits;
    }

    public void setTraits(String traits) {
        this.traits = traits;
    }
}


