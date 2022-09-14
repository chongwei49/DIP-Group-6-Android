package com.example.personalitytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Friends_Update extends AppCompatActivity {

    private ImageView button_back;
    private Button button_compatibility,button_chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_update);
        button_back = (ImageView) findViewById(R.id.backBtn);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tohomeactivity();
            }
        });

        button_compatibility = (Button) findViewById(R.id.CompatibiltyBtn);
        button_compatibility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tofriendscompatibilityactivity();
            }
        });

        button_chart = (Button) findViewById(R.id.chartBtn);
        button_chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tofriendschartactivity();
            }
        });
    }

    public void tohomeactivity() {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

    public void tofriendscompatibilityactivity() {
        Intent intent = new Intent(this, Friends_Compatibility.class);
        startActivity(intent);
    }

    public void tofriendschartactivity() {
        Intent intent = new Intent(this, Friends_Chart.class);
        startActivity(intent);
    }
}