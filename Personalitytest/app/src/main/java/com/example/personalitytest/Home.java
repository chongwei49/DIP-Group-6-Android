package com.example.personalitytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    private ImageView backimage;
    private TextView connect, profile;
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

        backimage = (ImageView) findViewById(R.id.homeBtn);
        backimage.setClickable(true);
        backimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeactivity();
            }
        });

        connect = (TextView) findViewById(R.id.friendsBtn);
        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toconnectactivity();
            }
        });

        profile = (TextView) findViewById(R.id.startQuizBtn);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totestsctivity();
            }
        });

        profile = (TextView) findViewById(R.id.profileBtn);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toprofileactivity();
            }
        });

    }
    public void toconnectactivity() {
        Intent intent = new Intent(this, connect_main.class);
        startActivity(intent);
    }

    public void toprofileactivity() {
        Intent intent = new Intent(this, userupdates.class);
        startActivity(intent);
    }

    public void homeactivity() {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

    public void totestsctivity() {
        Intent intent = new Intent(this, Tests.class);
        startActivity(intent);
    }
}