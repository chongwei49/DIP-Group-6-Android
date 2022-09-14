package com.ntu.dip.group6.personalitytestmobileapplicationservice.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.security.AuthProvider;

@Entity
@Data
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="userID")
    private Integer userId;
    private String name;
    private String email;
    private String username;
    private String password;
    private String dob;


    public User() {
    }

    public User( String name, String email, String username, String password, String DOB) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.dob = DOB;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userID) {
        this.userId = userID;
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

    public String getUsername() { return this.username; }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDob() {
        return this.dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}
