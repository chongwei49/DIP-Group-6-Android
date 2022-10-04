package com.example.personalitytest.profiles;

public class PersonalityProfiles {

    private int profilepic;
    private String name,age;

    public PersonalityProfiles(int profilepic, String name,String age){
        this.profilepic = profilepic;
        this.name = name;
        this.age = age;
    }

    public int getImage() {
        return profilepic;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }
}
