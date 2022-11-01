package com.example.personalitytest;

class Profile {

    int image;
    String name, age;

    public Profile(int image,String name, String age){
        this.image = image;
        this.name = name;
        this.age = age;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
