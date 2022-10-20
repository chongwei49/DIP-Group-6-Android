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

    public Integer getPriId() {
        return this.priId;
    }

    public void setPriId(Integer priId) {
        this.priId = priId;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getQnCategory() {
        return this.qnCategory;
    }

    public void setQnCategory(String qnCategory) {
        this.qnCategory = qnCategory;
    }

    public String getPersonalityType() {
        return this.personalityType;
    }

    public void setPersonalityType(String personalityType) {
        this.personalityType = personalityType;
    }

    public String getDateTime() {
        return this.dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
