package com.example.personalitytest.profiles;

public class LoveProfiles {
    private int image;
    private String name,age;

    public LoveProfiles(int image, String name,String age){
        this.image = image;
        this.name = name;
        this.age = age;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }
}
