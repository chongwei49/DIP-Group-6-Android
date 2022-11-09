package com.example.personalitytest.models;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

    public Integer userId;
    public String name;
    public String email;
    public String password;
    public String dob;
    public String gender;
    public byte[] profilePic;


    public User() {}

    public User(Integer userId, String name, String email, String password, String DOB, String gender, byte[] profilePic) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.dob = DOB;
        this.gender = gender;
        this.profilePic = profilePic;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

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

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public byte[] getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(byte[] profilePic) {
        this.profilePic = profilePic;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(userId);
        parcel.writeString(name);
        parcel.writeString(email);
        parcel.writeString(password);
        parcel.writeString(dob);
        parcel.writeString(gender);
        parcel.writeByteArray(profilePic);
    }

    protected User (Parcel in) {
        userId = in.readInt();
        name = in.readString();
        email = in.readString();
        password = in.readString();
        dob = in.readString();
        gender = in.readString();
        profilePic = in.createByteArray();
    }
}
