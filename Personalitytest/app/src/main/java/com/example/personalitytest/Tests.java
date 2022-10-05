package com.example.personalitytest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Tests extends AppCompatActivity implements View.OnClickListener {
    private CardView personalities, love, career;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tests);
        personalities = (CardView) findViewById(R.id.personalities);
        love = (CardView) findViewById(R.id.love);
        career = (CardView) findViewById(R.id.career);
        back = (ImageView) findViewById(R.id.back);

        personalities.setOnClickListener(this);
        love.setOnClickListener(this);
        career.setOnClickListener(this);
        back.setOnClickListener(this);

    }

    public void openNewActivity() {
        Intent intent = new Intent(this, QuizLove.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        Intent i;
        switch(view.getId()){
            case R.id.personalities:
                i =new Intent(this, QuizPersonality.class);
                startActivity(i);
                break;

            case R.id.love:
                i =new Intent(this, QuizLove.class);
                startActivity(i);
                break;

            case R.id.career:
                i =new Intent(this, QuizCareer.class);
                startActivity(i);
                break;

            case R.id.back:
                i = new Intent(this,Home.class);
                startActivity(i);
                break;
        }
    }
}
