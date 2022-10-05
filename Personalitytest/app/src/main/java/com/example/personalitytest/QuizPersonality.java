package com.example.personalitytest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class QuizPersonality extends AppCompatActivity {
    private Button buttonyes, buttonno;
    private ImageView homebutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizpersonality);
        buttonyes = (Button) findViewById(R.id.buttonyes);
        buttonyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toresultpersonalityactivity();
            }
        });
        buttonno = (Button) findViewById(R.id.buttonno);
        buttonno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toresultpersonalityactivity();
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

    public void toresultpersonalityactivity() {
        Intent intent = new Intent(this, ResultPersonality.class);
        startActivity(intent);
    }
    public void tohomeactivity() {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

}
