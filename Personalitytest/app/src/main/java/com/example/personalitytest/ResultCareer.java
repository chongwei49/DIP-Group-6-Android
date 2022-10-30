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
import com.example.personalitytest.models.User;

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
    private ArrayList<User> userInfo = new ArrayList<User>();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultcareer);

        Intent intent = getIntent();
        quiz_result = intent.getStringExtra("Result");
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

        getUserInfo();
        userId=userInfo.get(0).getUserId();
        userName=userInfo.get(0).getName();
        userEmail=userInfo.get(0).getEmail();
        userGender=userInfo.get(0).getGender();
        userDOB= userInfo.get(0).getDob();
        Log.d("User passing info test", String.valueOf(userId)+", "+userName);
    }

    public void tohomeactivity() {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

    public void getUserInfo(){
        if (getIntent().getExtras() != null) {
            Log.d("Bundle log", "Bundle not empty");
            userInformation = getIntent().getExtras();
            userInfo = userInformation.getParcelableArrayList("userInfo");
//            for(int i=0;i<userInfo.size();i++){
//                Log.d("TestsPage",userInfo.get(i).getName());
//            }
//            userId = userInformation.getInt("userId");
//            userName = userInformation.getString("name");
//            userEmail = userInformation.getString("email");
//            userGender = userInformation.getString("gender");
//            userDOB = userInformation.getString("dob");
        } else {
            Log.d("Error", "Bundle empty");

            SharedPreferences prefs = getSharedPreferences(USER_INFORMATION, MODE_PRIVATE);
            userId = prefs.getInt("userId", 0);
            userName = prefs.getString("name", "default");
            userEmail = prefs.getString("email", "default");
            userGender = prefs.getString("gender", "default");
            userDOB = prefs.getString("DOB", "default");

            Log.d("User name", "User Name, " + userName);

            userInformation.putInt("userId", userId);
            userInformation.putString("name", userName);
            userInformation.putString("email", userEmail);
            userInformation.putString("gender", userGender);
            userInformation.putString("dob", userDOB);

        }
    }

}
