package com.example.personalitytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class usersettings extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usersettings);

        button = (Button) findViewById(R.id.logout);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settingslogoutactivity();
            }
        });
        button = (Button) findViewById(R.id.settingsdone);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savesettingsactivity();
            }
        });

    }

    public void settingslogoutactivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void savesettingsactivity() {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

}