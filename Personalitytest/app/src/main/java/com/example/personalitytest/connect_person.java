package com.example.personalitytest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class connect_person extends AppCompatActivity {

    private TextView connect, profile;
    private ImageView backimage;
    private Button notdone_personaltystart, notdone_lovestart, notdone_careerstart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_person);

        backimage = (ImageView) findViewById(R.id.backtoconnectfrag);
        backimage.setClickable(true);
        backimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().
                        replace(R.id.connectperson,new connectFragment()).commit();

            }
        });

        notdone_personaltystart = (Button) findViewById(R.id.notdone_personaltystart);
        notdone_personaltystart.setClickable(true);
        notdone_personaltystart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                personalityactivity();
            }
        });
        notdone_lovestart = (Button) findViewById(R.id.notdone_lovestart);
        notdone_lovestart.setClickable(true);
        notdone_lovestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              loveactivity();
            }
        });

        notdone_careerstart = (Button) findViewById(R.id.notdone_careerstart);
        notdone_careerstart.setClickable(true);
        notdone_careerstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                careeractivity();
            }
        });


    }



    public void homeactivity() {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

    public void mainactivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void personalityactivity() {
        Intent intent = new Intent(this, QuizPersonality.class);
        startActivity(intent);
    }

    public void loveactivity() {
        Intent intent = new Intent(this, QuizLove.class);
        startActivity(intent);
    }

    public void careeractivity() {
        Intent intent = new Intent(this, QuizCareer.class);
        startActivity(intent);
    }

}
