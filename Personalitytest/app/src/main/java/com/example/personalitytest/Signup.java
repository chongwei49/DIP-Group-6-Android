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

        View decorView = getWindow().getDecorView();
        // Hide both the navigation bar and the status bar.
        // SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
        // a general rule, you should design your app to hide the status bar whenever you
        // hide the navigation bar.

        backimage = (ImageView) findViewById(R.id.signUpBck);
        backimage.setClickable(true);
        backimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainactivity();
            }
        });

        button = (Button) findViewById(R.id.signupBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeactivity();
            }
        });
    }

    public void mainactivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void homeactivity() {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }
}