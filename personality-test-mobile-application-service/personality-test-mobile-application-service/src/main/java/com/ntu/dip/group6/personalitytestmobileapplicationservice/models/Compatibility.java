package com.ntu.dip.group6.personalitytestmobileapplicationservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
public class Compatibility {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer priId;
    private Integer compatibilityId;
    private Integer userId;
    private String qnCategory;
    private Integer qnId;
    private String qns;
    private boolean answer;
    private boolean responded;

    public Compatibility() {

    }

    public Compatibility(Integer compatibilityId, Integer userId, String qnCategory, Integer qnId, String qns, boolean answer, boolean responded) {
        this.compatibilityId = compatibilityId;
        this.userId = userId;
        this.qnCategory = qnCategory;
        this.qnId = qnId;
        this.qns = qns;
        this.answer = answer;
        this.responded = responded;
    }


    public Integer getPriId() {
        return priId;
    }

    public Integer getCompatibilityId() {
        return compatibilityId;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getQnCategory() {
        return qnCategory;
    }

    public Integer getQnId() {
        return qnId;
    }

    public String getQns() {
        return qns;
    }

    public Boolean getAnswer() {
        return answer;
    }

    public Boolean getResponded() {
        return responded;
    }

    public void setPriId(Integer priId) {
        this.priId = priId;
    }

    public void setCompatibilityId(Integer compatibilityId) {
        this.compatibilityId = compatibilityId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setQnCategory(String qnCategory) {
        this.qnCategory = qnCategory;
    }

    public void setQnId(Integer qnId) {
        this.qnId = qnId;
    }

    public void setQns(String qns) {
        this.qns = qns;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    public void setResponded(boolean responded) {
        this.responded = responded;
    }
}
