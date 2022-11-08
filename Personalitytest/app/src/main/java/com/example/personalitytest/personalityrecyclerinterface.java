package com.example.personalitytest;

import com.example.personalitytest.models.User;

import java.util.ArrayList;

public interface personalityrecyclerinterface {
    void onItemClick(int position);

    void onItemClick(int position, ArrayList<User> arrayList);
}

