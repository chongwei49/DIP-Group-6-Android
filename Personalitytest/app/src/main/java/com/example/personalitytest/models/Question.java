package com.example.personalitytest.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Question implements Parcelable {
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

    public Integer getPriId() {
        return this.priId;
    }

    public void setPriId(Integer priId) {
        this.priId = priId;
    }

    public Integer getQnId() {
        return this.qnId;
    }

    public void setQnId(Integer qnId) {
        this.qnId = qnId;
    }

    public String getQnCategory() {
        return this.qnCategory;
    }

    public void setQnCategory(String qnCategory) {
        this.qnCategory = qnCategory;
    }

    public String getQns() {
        return this.qns;
    }

    public void setQns(String qns) {
        this.qns = qns;
    }

    public Boolean getAnswer() {
        return this.answer;
    }

    public void setAnswer(Boolean answer) {
        this.answer = answer;
    }

    public String getTraits() {
        return this.traits;
    }

    public void setTraits(String traits) {
        this.traits = traits;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(priId);
        parcel.writeInt(qnId);
        parcel.writeString(qns);
        parcel.writeString(qnCategory);
        parcel.writeBoolean(answer);
        parcel.writeString(traits);
    }

    protected Question(Parcel in) {
        priId = in.readInt();
        qnId = in.readInt();
        qns = in.readString();
        qnCategory = in.readString();
        answer = in.readBoolean();
        traits = in.readString();
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };
}


