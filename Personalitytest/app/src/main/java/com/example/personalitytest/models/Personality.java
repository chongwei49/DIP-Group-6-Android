package com.example.personalitytest.models;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Personality implements Parcelable {
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
        //this.dateTime = dateTime;
        this.dateTime = dateTime.substring(0, dateTime.length()-10);
    }

    public static final Creator<Personality> CREATOR = new Creator<Personality>() {
        @Override
        public Personality createFromParcel(Parcel in) {
            return new Personality(in);
        }

        @Override
        public Personality[] newArray(int size) {
            return new Personality[size];
        }
    };

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

    @RequiresApi(api = Build.VERSION_CODES.O)
    public LocalDate getDateTimeCMP(){
        return LocalDate.parse(this.dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(priId);
        parcel.writeInt(userId);
        parcel.writeString(qnCategory);
        parcel.writeString(personalityType);
        parcel.writeString(dateTime);
    }

    protected Personality (Parcel in) {
        priId = in.readInt();
        userId = in.readInt();
        qnCategory = in.readString();
        personalityType = in.readString();
        dateTime = in.readString();
    }
}
