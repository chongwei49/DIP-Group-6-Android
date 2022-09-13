package com.example.personalitytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class usercharts extends AppCompatActivity {

    private ImageView backimage;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usercharts);

        backimage = (ImageView) findViewById(R.id.chartsback);
        backimage.setClickable(true);
        backimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chartsbackactivity();
            }
        });

        button = (Button) findViewById(R.id.chartstoupdates);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toupdatesactivity();
            }
        });
        button = (Button) findViewById(R.id.chartstosettings);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tosettingsactivity();
            }
        });

    }

    public void chartsbackactivity() {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }
    public void toupdatesactivity() {
        Intent intent = new Intent(this, userupdates.class);
        startActivity(intent);
    }
    public void tosettingsactivity() {
        Intent intent = new Intent(this, usersettings.class);
        startActivity(intent);
    }
}