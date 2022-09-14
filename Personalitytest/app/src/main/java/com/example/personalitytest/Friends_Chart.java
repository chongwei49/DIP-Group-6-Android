package com.example.personalitytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Friends_Chart extends AppCompatActivity {

    private ImageView button_back;
    private Button button_compatibility, button_updates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_chart);

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

        button_updates = (Button) findViewById(R.id.updatesBtn);
        button_updates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tofriendsupdatesactivity();
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

        public void tofriendsupdatesactivity() {
            Intent intent = new Intent(this, Friends_Update.class);
            startActivity(intent);
        }
}