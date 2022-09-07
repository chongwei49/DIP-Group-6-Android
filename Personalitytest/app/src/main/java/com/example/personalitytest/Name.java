package com.example.personalitytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Name extends AppCompatActivity {

    private ImageView backimage;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        backimage = (ImageView) findViewById(R.id.usernameBck);
        backimage.setClickable(true);
        backimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailactivity();
            }
        });

        button = (Button) findViewById(R.id.usernameBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayactivity();
            }
        });
    }

    public void displayactivity() {
        Intent intent = new Intent(this, Displayname.class);
        startActivity(intent);
    }

    public void emailactivity() {
        Intent intent = new Intent(this, Email.class);
        startActivity(intent);
    }
}