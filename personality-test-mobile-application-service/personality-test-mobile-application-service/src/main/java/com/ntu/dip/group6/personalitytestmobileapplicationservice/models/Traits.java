package com.ntu.dip.group6.personalitytestmobileapplicationservice.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "traits")
public class Traits {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer priId;
    private String quizCategory;
    private String trait;
    private String description;

    public Traits() {}

    public Traits(Integer priId, String quizCategory, String trait, String description) {
        this.priId = priId;
        this.quizCategory = quizCategory;
        this.trait = trait;
        this.description = description;
    }

    public Integer getPriId() {
        return priId;
    }

    public void setPriId(Integer priId) {
        this.priId = priId;
    }

    public String getQuizCategory() {
        return quizCategory;
    }

    public void setQuizCategory(String quizCategory) {
        this.quizCategory = quizCategory;
    }

    public String getTrait() {
        return trait;
    }

    public void setTrait(String trait) {
        this.trait = trait;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
