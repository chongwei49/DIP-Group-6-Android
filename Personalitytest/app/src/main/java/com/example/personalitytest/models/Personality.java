package com.example.personalitytest.models;

public class Personality {
    public int priId;
    public int userId;
    public String qnCategory;
    public String personalityType;
    public String dateTime;


    public Personality() {}

    public Personality(int priId, int userId, String qnCategory, String personalityType, String dateTime) {
        this.priId = priId;
        this.userId = userId;
        this.qnCategory = qnCategory;
        this.personalityType = personalityType;
        this.dateTime = dateTime;
    }
}
