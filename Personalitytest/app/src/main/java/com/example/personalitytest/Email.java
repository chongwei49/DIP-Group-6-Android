package com.example.personalitytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Email extends AppCompatActivity {

    private ImageView backimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        backimage = (ImageView) findViewById(R.id.emailBck);
        backimage.setClickable(true);
        backimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backmainactivity();
            }
        });
    }

    public void backmainactivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}