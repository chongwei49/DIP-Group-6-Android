package com.ntu.dip.group6.personalitytestmobileapplicationservice.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @Column(name="userID")
    private String userID;

    private String name;
    private String email;
    private String password;
    private String phone_number;
    private String date_of_birth;

    public User() {

    }
    public User(String userID, String name, String email, String password, String phoneNumber, String DOB) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone_number = phoneNumber;
        this.date_of_birth = DOB;
    }

    public String getUserID() {
        return this.userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone_number() {
        return this.phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getDate_of_birth() {
        return this.date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }
}
