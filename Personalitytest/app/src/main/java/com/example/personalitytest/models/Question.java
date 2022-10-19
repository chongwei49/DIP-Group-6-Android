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
}


