package com.example.personalitytest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.personalitytest.models.User;

import java.util.ArrayList;

public class Tests extends AppCompatActivity{
    private CardView personalities;
    private CardView love;
    private CardView career;
    private ImageView back;

    private String USER_INFORMATION;
    private Integer userId;
    private String userName;
    private String userEmail;
    private String userGender;
    private String userDOB;
    private Bundle userInformation = new Bundle();
    private ArrayList<User> userInfo = new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tests);
        personalities = (CardView) findViewById(R.id.personalities);
        personalities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createBundle();
                toquizpersonalityactivity(userInformation);
            }
        });
        love = (CardView) findViewById(R.id.love);
        love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createBundle();
                toquizloveactivity(userInformation);
            }
        });
        career = (CardView) findViewById(R.id.career);
        career.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createBundle();
                toquizcareeractivity(userInformation);
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

        getUserInfo();

    }

    public void toquizpersonalityactivity(Bundle bundle) {
        Intent intent = new Intent(this, QuizPersonality.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    public void toquizloveactivity(Bundle bundle) {
        Intent intent = new Intent(this, QuizLove.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    public void toquizcareeractivity(Bundle bundle) {
        Intent intent = new Intent(this, QuizCareer.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    public void tohomeactivity() {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }


    public void createBundle(){
        userInformation.putParcelableArrayList("userInfo",userInfo);
//        userInformation.putInt("userId", userId);
//        userInformation.putString("name", userName);
//        userInformation.putString("email", userEmail);
//        userInformation.putString("gender", userGender);
//        userInformation.putString("dob", userDOB);
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
