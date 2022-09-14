package com.example.personalitytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class Friends_Page extends AppCompatActivity {

    private RelativeLayout button;
    private ImageView backimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_page);

        button = (RelativeLayout) findViewById(R.id.friends_deep);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tofriendscompatibilityactivity();
            }
        });

        backimage = (ImageView) findViewById(R.id.friendsBck);
        backimage.setClickable(true);
        backimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeactivity();
            }
        });
    }

    public void tofriendscompatibilityactivity() {
        Intent intent = new Intent(this, Friends_Compatibility.class);
        startActivity(intent);
    }

    public void homeactivity() {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }
}