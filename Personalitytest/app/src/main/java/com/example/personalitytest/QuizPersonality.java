package com.example.personalitytest;

import android.app.ProgressDialog;
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class QuizPersonality extends AppCompatActivity implements Serializable {
    private Button buttonyes;
    private Button buttonno;
    private ImageView homebutton;
    private TextView questionTV, indexView, questionIndexView;
    private int qCounter = 1, quizSize;
    private ArrayList<Question> personalityQuizVar = new ArrayList<Question>();
    private ArrayList<Question> personalityQuizAns = new ArrayList<Question>();
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizpersonality);
        buttonyes = (Button) findViewById(R.id.buttonyes);
        buttonyes.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {

                for(int z=0; z<personalityQuizVar.size(); z++){
                    if(personalityQuizVar.get(z).getQnId() == qCounter && personalityQuizVar.get(z).getAnswer()){
                        personalityQuizAns.add(personalityQuizVar.get(z));
                    }
                }
                qCounter=qCounter+1;
                if(qCounter>quizSize){
                    toresultpersonalityactivity();
                }else{
                    toNextQuestion();
                }
            }

        });
        buttonno = (Button) findViewById(R.id.buttonno);
        buttonno.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                for(int z=0; z<personalityQuizVar.size(); z++){
                    if(personalityQuizVar.get(z).getQnId() == qCounter && !personalityQuizVar.get(z).getAnswer()){
                        personalityQuizAns.add(personalityQuizVar.get(z));
                    }
                }
                qCounter=qCounter+1;
                if(qCounter>quizSize){
                    toresultpersonalityactivity();
                }else{
                    toNextQuestion();
                }
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
        questionTV.setText("Question "+qCounter);
        //Log.d("Total Num of Qns: ", String.valueOf(totalQuestions));

        questionIndexView = (TextView) findViewById(R.id.textView49);

        indexView = (TextView) findViewById(R.id.textView52);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        Log.d("Intent_check", String.valueOf(intent.getExtras()));
        dialog = ProgressDialog.show(QuizPersonality.this, "",
                "Loading. Please wait...", true);
        if(intent.getExtras().containsKey("Ans_list")){//previously = intent.getExtras()!=null
            personalityQuizVar= intent.getParcelableArrayListExtra("Question_list");
            personalityQuizAns= intent.getParcelableArrayListExtra("Ans_list");
            qCounter = intent.getIntExtra("Question_Counter", 1);
            quizSize = intent.getIntExtra("Quiz_size", 1);
            setUpPage();
        }else{
            Services.getAllQuestions(QuizPersonality.this, new Services.QuestionCallback() {
                @Override
                public void onSuccess(ArrayList<Question> result) {
                    qCounter = 1;
                    for(int i=0;i<result.size();i++){
                        if(result.get(i).getQnCategory().equals("16Personalities")){
                            personalityQuizVar.add(result.get(i));
                        }
                    }
                    quizSize = personalityQuizVar.size() / 2;
                    setUpPage();
                }
            });
        }

        for(int i=0;i<personalityQuizAns.size();i++){
            //preCalc.add(String.valueOf(personalityQuizAns.get(i).getAnswer()));
            //Log.d("ans size", String.valueOf(personalityQuizAns.size()));
            Log.d("Answer",String.valueOf(personalityQuizAns.get(i).getAnswer()));
            //Log.d("Answer",preCalc.get(i));
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void toresultpersonalityactivity() {
        Intent intent = new Intent(QuizPersonality.this, ResultPersonality.class);
        //intent.putExtra("Result", calculateResult());
        intent.putExtra("questionAnswers",personalityQuizAns); //help! how to fix this?
        startActivity(intent);

        finish();
    }

    public void tohomeactivity() {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);

        finish();
    }
    public void toNextQuestion(){
        Intent intent = new Intent(this, QuizPersonality.class);

        intent.putExtra("Question_list", personalityQuizVar);
        intent.putExtra("Ans_list", personalityQuizAns);
        intent.putExtra("Question_Counter", qCounter);
        intent.putExtra("Quiz_size", quizSize);
        startActivity(intent);

        finish();
    }
    public void setUpPage(){
        for(int x=0;x<personalityQuizVar.size();x++){
            Log.d("Question_List","QID:"+personalityQuizVar.get(x).getQnId()+" Question:"+personalityQuizVar.get(x).getQns()+" Ans:"+personalityQuizVar.get(x).getAnswer());
            if(personalityQuizVar.get(x).getQnId()==qCounter){
                questionTV.setText(personalityQuizVar.get(x).getQns());
            }
        }
        questionIndexView.setText("Question "+qCounter);
        indexView.setText(qCounter+"/"+quizSize);
        dialog.cancel();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String calculateResult(){
        HashMap<String, Integer> resultMap = new HashMap<>();
        for(int i=0; i<personalityQuizAns.size(); i++){
            if(!resultMap.containsKey(personalityQuizAns.get(i).getTraits())){
                resultMap.put(personalityQuizAns.get(i).getTraits(), 0);
            }else{
                resultMap.merge(personalityQuizAns.get(i).getTraits(), 1, Integer::sum);
            }
        }

        int maxValueInMap=(Collections.max(resultMap.values()));  // This will return max value in the HashMap
        for (Map.Entry<String, Integer> entry : resultMap.entrySet()) {  // Iterate through HashMap
            if (entry.getValue()==maxValueInMap) {
                return entry.getKey();
            }
        }
        return null;
    }
}
