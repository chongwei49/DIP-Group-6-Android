package com.example.personalitytest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultLove extends AppCompatActivity {
    private ImageView homebutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultlove);
        homebutton = (ImageView) findViewById(R.id.homebutton);
        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tohomeactivity();
            }
        });
    }
    public void tohomeactivity() {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

}
