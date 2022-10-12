package com.example.personalitytest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class connect_person extends AppCompatActivity {

    private TextView connect, profile;
    private ImageView backimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_person);


    }

    public void toconnectactivity() {
        Intent intent = new Intent(this, connect_main.class);
        startActivity(intent);
    }

    public void toprofileactivity() {
        Intent intent = new Intent(this, userupdates.class);
        startActivity(intent);
    }

    public void homeactivity() {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }
}
