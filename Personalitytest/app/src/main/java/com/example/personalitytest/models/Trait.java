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
}
