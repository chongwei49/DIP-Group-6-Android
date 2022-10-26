package com.example.personalitytest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import com.example.personalitytest.models.Trait;

import java.util.ArrayList;

public class ResultCareer extends AppCompatActivity {
    private ImageView homebutton;
    private TextView resultView, descView;
    private String quiz_result, description="";

    private String USER_INFORMATION;
    private Integer userId;
    private String userName;
    private String userEmail;
    private String userGender;
    private String userDOB;
    private Bundle userInformation = new Bundle();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultcareer);

        Intent intent = getIntent();
        quiz_result = intent.getStringExtra("Result");
        userId = intent.getIntExtra("User_ID", 0);
        ProgressDialog dialog = ProgressDialog.show(ResultCareer.this, "",
                "Loading. Please wait...", true);
        Services.getAllTraits(ResultCareer.this, new Services.TraitCallback() {
            @Override
            public void onSuccess(ArrayList<Trait> result) {
                for(int i=0; i<result.size(); i++){
                    Log.d("Trait Names", result.get(i).getTraitName());
                    if(result.get(i).getTraitName().contains(quiz_result)){
                        description = result.get(i).getDescription();
                    }
                }
                resultView.setText(quiz_result);
                descView.setText(description);
                dialog.cancel();
            }
        });

        resultView = findViewById(R.id.textView9);
        descView = findViewById(R.id.textView10);


        homebutton = (ImageView) findViewById(R.id.homebutton);
        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tohomeactivity();
            }
        });

        Log.d("USER_ID", String.valueOf(userId));
    }

    public void tohomeactivity() {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }



}
