package com.example.personalitytest.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Trait implements Parcelable {
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

    public static final Creator<Trait> CREATOR = new Creator<Trait>() {
        @Override
        public Trait createFromParcel(Parcel in) {
            return new Trait(in);
        }

        @Override
        public Trait[] newArray(int size) {
            return new Trait[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(priId);
        parcel.writeString(quizCategory);
        parcel.writeString(personalityType);
        parcel.writeString(traitName);
        parcel.writeString(description);
    }

    protected Trait(Parcel in) {
        priId = in.readInt();
        quizCategory = in.readString();
        personalityType = in.readString();
        traitName = in.readString();
        description = in.readString();
    }
}
