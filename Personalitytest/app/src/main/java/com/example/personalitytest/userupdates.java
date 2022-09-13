package com.example.personalitytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class userupdates extends AppCompatActivity {


    private ImageView backimage;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userupdates);

        backimage = (ImageView) findViewById(R.id.updatesback);
        backimage.setClickable(true);
        backimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatesbackactivity();
            }
        });

        button = (Button) findViewById(R.id.updatestochart);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tochartactivity();
            }
        });
        button = (Button) findViewById(R.id.updatestosettings);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tosettingsactivity();
            }
        });

    }

    public void updatesbackactivity() {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }
    public void tochartactivity() {
        Intent intent = new Intent(this, usercharts.class);
        startActivity(intent);
    }
    public void tosettingsactivity() {
        Intent intent = new Intent(this, usersettings.class);
        startActivity(intent);
    }
}