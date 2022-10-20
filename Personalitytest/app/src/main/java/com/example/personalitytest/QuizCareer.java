package com.example.personalitytest;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.personalitytest.models.Question;

import java.util.ArrayList;

public class QuizCareer extends AppCompatActivity{
    private Button buttonyes;
    private Button buttonno;
    private ImageView homebutton;
    private TextView questionTV;
    private int qCounter=1,totalQuestions=0;
    private String question;
    private ArrayList<Question> careerQuizVar = new ArrayList<Question>();
    private ArrayList<Question> careerQuizAns = new ArrayList<Question>();
    private Bundle careerAns = new Bundle();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizcareer);

        if(careerAns==null){
            Services.getAllQuestions(QuizCareer.this, new Services.QuestionCallback() {
                @Override
                public void onSuccess(ArrayList<Question> result) {
                    Bundle careerAns = new Bundle();

                    for(int i=0;i<result.size();i++){
                        if(result.get(i).getQnCategory().equals("Job")){
                            careerQuizVar.add(result.get(i));
                            //Log.d("Career Questions", String.valueOf(result.get(i).getQns()));
                        }
                    }


                    totalQuestions=careerQuizVar.size()/2;

                }
            });
        }

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

        questionTV = (TextView) findViewById(R.id.textView41);
        //Log.d("Total Num of Qns: ", String.valueOf(totalQuestions));
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
