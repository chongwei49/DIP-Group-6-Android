package com.example.personalitytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    private Button button;
    private TextView button_friends;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        button = (Button) findViewById(R.id.chartBtn);
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