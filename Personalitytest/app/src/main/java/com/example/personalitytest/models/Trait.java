package com.example.personalitytest.models;

public class Trait {
    public int priId;
    public String quizCategory;
    public String personalityType;
    public String traitName;
    public String description;

    public Trait() {}

    public Trait(int priId, String quizCategory, String personalityType, String traitName, String description) {
        this.priId = priId;
        this.quizCategory = quizCategory;
        this.personalityType = personalityType;
        this.traitName = traitName;
        this.description = description;
    }

    public Integer getPriId() {
        return this.priId;
    }

    public void setPriId(Integer priId) {
        this.priId = priId;
    }

    public String getQuizCategory() {
        return this.quizCategory;
    }

    public void setQuizCategory(String quizCategory) {
        this.quizCategory = quizCategory;
    }

    public String getPersonalityType() {
        return this.personalityType;
    }

    public void setPersonalityType(String personalityType) {
        this.personalityType = personalityType;
    }

    public String getTraitName() {
        return this.traitName;
    }

    public void setTraitName(String traitName) {
        this.traitName = traitName;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
