package com.example.personalitytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    private TextView button;
    private TextView button_friends;
    private TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Bundle userInformation = getIntent().getExtras();

        String userName = userInformation.getString("name");
        String userEmail = userInformation.getString("email");
        String userDOB = userInformation.getString("dob");

        name = findViewById(R.id.name);
        name.setText(userName);

        button = (TextView) findViewById(R.id.profileBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tochartactivity();
            }
        });

        button_friends = (TextView) findViewById(R.id.friendsBtn);
        button_friends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tofriendsactivity();
            }
        });
    }
    public void tochartactivity() {
        Intent intent = new Intent(this, userupdates.class);
        startActivity(intent);
    }

    public void tofriendsactivity() {
        Intent intent = new Intent(this, Friends_Page.class);
        startActivity(intent);
    }
}