package com.example.personalitytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Displayname extends AppCompatActivity {

    private ImageView backimage;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displayname);

        backimage = (ImageView) findViewById(R.id.displayBck);
        backimage.setClickable(true);
        backimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameactivity();
            }
        });

        button = (Button) findViewById(R.id.displayBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passwordactivity();
            }
        });
    }

    public void nameactivity() {
        Intent intent = new Intent(this, Name.class);
        startActivity(intent);
    }

    public void passwordactivity() {
        Intent intent = new Intent(this, Password.class);
        startActivity(intent);
    }
}