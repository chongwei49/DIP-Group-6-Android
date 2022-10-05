package com.example.personalitytest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class QuizCareer extends AppCompatActivity{
    private Button buttonyes;
    private Button buttonno;
    private ImageView homebutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizcareer);
        buttonyes = (Button) findViewById(R.id.buttonyes);
        buttonyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toresultcareeractivity();
            }
        });
        buttonno = (Button) findViewById(R.id.buttonno);
        buttonno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toresultcareeractivity();
            }
        });
        homebutton = (ImageView) findViewById(R.id.homebutton);
        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tohomeactivity();
            }
        });

    }

    public void toresultcareeractivity() {
        Intent intent = new Intent(this, ResultCareer.class);
        startActivity(intent);
    }
    public void tohomeactivity() {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }


}
