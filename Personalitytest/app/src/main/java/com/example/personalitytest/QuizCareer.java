package com.example.personalitytest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
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
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class QuizCareer extends AppCompatActivity{
    private Button buttonyes;
    private Button buttonno;
    private ImageView homebutton;
    private TextView questionTV, indexView, questionIndexView;
    private int qCounter = 1, quizSize;
    private ArrayList<Question> careerQuizVar = new ArrayList<Question>();
    private ArrayList<Question> careerQuizAns = new ArrayList<Question>();
    ProgressDialog dialog;

    private String USER_INFORMATION;
    private Integer userId;
    private String userName;
    private String userEmail;
    private String userGender;
    private String userDOB;
    private Bundle userInformation = new Bundle();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizcareer);

        buttonyes = (Button) findViewById(R.id.buttonyes);
        buttonyes.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                for(int z=0; z<careerQuizVar.size(); z++){
                    if(careerQuizVar.get(z).getQnId() == qCounter && careerQuizVar.get(z).getAnswer()){
                        careerQuizAns.add(careerQuizVar.get(z));
                    }
                }
                qCounter=qCounter+1;
                if(qCounter>quizSize){
                    toresultcareeractivity();
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
                for(int z=0; z<careerQuizVar.size(); z++){
                    if(careerQuizVar.get(z).getQnId() == qCounter && !careerQuizVar.get(z).getAnswer()){
                        careerQuizAns.add(careerQuizVar.get(z));
                    }
                }
                qCounter=qCounter+1;
                if(qCounter>quizSize){
                    toresultcareeractivity();
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
        dialog = ProgressDialog.show(QuizCareer.this, "",
                "Loading. Please wait...", true);
        if(intent.getExtras().containsKey("Ans_list")){
            careerQuizVar= intent.getParcelableArrayListExtra("Question_list");
            careerQuizAns= intent.getParcelableArrayListExtra("Ans_list");
            qCounter = intent.getIntExtra("Question_Counter", 1);
            quizSize = intent.getIntExtra("Quiz_size", 1);
            setUpPage();
        }else{
            Services.getAllQuestions(QuizCareer.this, new Services.QuestionCallback() {
                @Override
                public void onSuccess(ArrayList<Question> result) {
                    qCounter = 1;
                    for(int i=0;i<result.size();i++){
                        if(result.get(i).getQnCategory().equals("Job")){
                            careerQuizVar.add(result.get(i));
                        }
                    }
                    quizSize = careerQuizVar.size() / 2;
                    setUpPage();
                }
            });
        }

        for(int i=0;i<careerQuizAns.size();i++){
            Log.d("Career_answer", String.valueOf(careerQuizAns.get(i).getAnswer()));
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void toresultcareeractivity() {
        Intent intent = new Intent(this, ResultCareer.class);
        intent.putExtra("Result", calculateResult());
        startActivity(intent);
    }
    public void tohomeactivity() {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

    public void toNextQuestion(){
        Intent intent = new Intent(this, QuizCareer.class);
        intent.putExtra("Question_list", careerQuizVar);
        intent.putExtra("Ans_list", careerQuizAns);
        intent.putExtra("Question_Counter", qCounter);
        intent.putExtra("Quiz_size", quizSize);
        startActivity(intent);
        finish();
    }

    public void setUpPage(){
        for(int x=0;x<careerQuizVar.size();x++){
            Log.d("Question_List","QID:"+careerQuizVar.get(x).getQnId()+" Question:"+careerQuizVar.get(x).getQns()+" Ans:"+careerQuizVar.get(x).getAnswer());
            if(careerQuizVar.get(x).getQnId()==qCounter){
                questionTV.setText(careerQuizVar.get(x).getQns());
            }
        }
        questionIndexView.setText("Question "+qCounter);
        indexView.setText(qCounter+"/"+quizSize);
        dialog.cancel();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String calculateResult(){
        HashMap<String, Integer> resultMap = new HashMap<>();
        for(int i=0; i<careerQuizAns.size(); i++){
            if(!resultMap.containsKey(careerQuizAns.get(i).getTraits())){
                resultMap.put(careerQuizAns.get(i).getTraits(), 1);
            }else{
                resultMap.merge(careerQuizAns.get(i).getTraits(), 1, Integer::sum);
            }
        }

        int sum = 0;
        for (int f : resultMap.values()) {
            sum += f;
        }
        Log.d("CareerQuizSize", String.valueOf(sum));

        int maxValueInMap=(Collections.max(resultMap.values()));  // This will return max value in the HashMap
        for (Map.Entry<String, Integer> entry : resultMap.entrySet()) {  // Iterate through HashMap
            if (entry.getValue()==maxValueInMap) {
                return entry.getKey();
            }
        }

        return null;
    }





}
