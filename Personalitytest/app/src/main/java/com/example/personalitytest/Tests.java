package com.example.personalitytest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Tests extends AppCompatActivity{
    private CardView personalities;
    private CardView love;
    private CardView career;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tests);
        personalities = (CardView) findViewById(R.id.personalities);
        personalities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toquizpersonalityactivity();
            }
        });
        love = (CardView) findViewById(R.id.love);
        love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toquizloveactivity();
            }
        });
        career = (CardView) findViewById(R.id.career);
        career.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toquizcareeractivity();
            }
        });
        back = (ImageView) findViewById(R.id.testsbacktohome);
        back.setClickable(true);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tohomeactivity();

            }
        });

    }

    public void toquizpersonalityactivity() {
        Intent intent = new Intent(this, QuizPersonality.class);
        startActivity(intent);
    }
    public void toquizloveactivity() {
        Intent intent = new Intent(this, QuizLove.class);
        startActivity(intent);
    }
    public void toquizcareeractivity() {
        Intent intent = new Intent(this, QuizCareer.class);
        startActivity(intent);
    }
    public void tohomeactivity() {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

}
