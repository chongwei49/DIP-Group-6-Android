package com.example.personalitytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class hist_personality extends AppCompatActivity {

    private ImageView backimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hist_personality);

        backimage = (ImageView) findViewById(R.id.backtoresults);
        backimage.setClickable(true);
        backimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backtoresultsactivity();
            }
        });
    }

    public void backtoresultsactivity() {
        Intent intent = new Intent(this, userupdates.class);
        startActivity(intent);
    }

}