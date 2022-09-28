package com.ntu.dip.group6.personalitytestmobileapplicationservice.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "personality")
public class Personality {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer priId;
    private Integer userId;
    private String qnCategory;
    private String personalityType;
    private Date dateTime;

    public Personality() {}

    public Personality(Integer userId, String qnCategory, String personalityType, Date dateTime) {
        this.userId = userId;
        this.qnCategory = qnCategory;
        this.personalityType = personalityType;
        this.dateTime = dateTime;
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

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
}
