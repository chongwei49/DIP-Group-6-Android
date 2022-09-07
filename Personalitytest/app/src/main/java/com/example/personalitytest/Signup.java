package com.example.personalitytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Signup extends AppCompatActivity {

    private ImageView backimage;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        backimage = (ImageView) findViewById(R.id.signUpBck);
        backimage.setClickable(true);
        backimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passwordactivity();
            }
        });

        button = (Button) findViewById(R.id.signupBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                birthplaceactivity();
            }
        });
    }

    public void passwordactivity() {
        Intent intent = new Intent(this, Password.class);
        startActivity(intent);
    }

    public void birthplaceactivity() {
        Intent intent = new Intent(this, BirthPlace.class);
        startActivity(intent);
    }
}