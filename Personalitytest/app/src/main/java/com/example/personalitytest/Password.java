package com.example.personalitytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Password extends AppCompatActivity {

    private ImageView backimage;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        backimage = (ImageView) findViewById(R.id.passwordBck);
        backimage.setClickable(true);
        backimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayactivity();
            }
        });

        button = (Button) findViewById(R.id.passwordBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signupactivity();
            }
        });
    }

    public void displayactivity() {
        Intent intent = new Intent(this, Displayname.class);
        startActivity(intent);
    }

    public void signupactivity() {
        Intent intent = new Intent(this, Signup.class);
        startActivity(intent);
    }
}