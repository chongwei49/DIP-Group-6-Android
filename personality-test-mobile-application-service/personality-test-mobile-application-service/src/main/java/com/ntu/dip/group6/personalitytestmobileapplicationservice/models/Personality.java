package com.ntu.dip.group6.personalitytestmobileapplicationservice.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Personality {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer priId;
    private Integer userId;
    private String qnCategory;
    private String personalityType;
    private String datatime;

    public Personality() {}

    public Personality(Integer userId, String qnCategory, String personalityType, String datetime) {
        this.userId = userId;
        this.qnCategory = qnCategory;
        this.personalityType = personalityType;
        this.datatime = datetime;
    }


    public Integer getPriId() {
        return priId;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getQnCategory() {
        return qnCategory;
    }

    public String getPersonalityType() {
        return personalityType;
    }

    public String getDatatime() {
        return datatime;
    }

    public void setPriId(Integer priId) {
        this.priId = priId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setQnCategory(String qnCategory) {
        this.qnCategory = qnCategory;
    }

    public void setPersonalityType(String personalityType) {
        this.personalityType = personalityType;
    }

    public void setDatatime(String datatime) {
        this.datatime = datatime;
    }
}
