package com.example.personalitytest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
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

public class ResultLove extends AppCompatActivity {
    private ImageView homebutton;
    private TextView resultView, descView;
    private String quiz_result, description="";
    private ImageView loveAvatar;

    private ArrayList<User> userInfo = new ArrayList<User>();
    private String USER_INFORMATION;
    private Integer userId;
    private String userName;
    private String userEmail;
    private String userGender;
    private String userDOB;
    private Bundle userInformation = new Bundle();
    private ArrayList<Trait> traitInfo = new ArrayList<Trait>();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultlove);

        Intent intent = getIntent();
        quiz_result = intent.getStringExtra("Result");

        getUserInfo();
        userId=userInfo.get(0).getUserId();
        userName=userInfo.get(0).getName();
        userEmail=userInfo.get(0).getEmail();
        userGender=userInfo.get(0).getGender();
        userDOB= userInfo.get(0).getDob();
        Log.d("Love passing infotest", String.valueOf(userId)+", "+userName);

        AsyncTaskRunner serviceTask = new AsyncTaskRunner();
        serviceTask.execute();

        resultView = findViewById(R.id.textView9);
        descView = findViewById(R.id.textView10);
        loveAvatar = findViewById(R.id.loveavatar);


        homebutton = (ImageView) findViewById(R.id.homebutton);
        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tohomeactivity();
            }
        });


    }
    public void tohomeactivity() {
        finish();
    }
    public int getImageName(String result) {

        switch(result) {
            case "Quality Time":
                return R.drawable.qualitytime;
            case "Act of Service":
                return R.drawable.actofservice;
            case "Physical Touch":
                return R.drawable.physicaltouch;
            case "Words of Affirmation":
                return R.drawable.wordsofaffirmation;
            default:
                return 0;
        }
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

    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        private String resp;
        ProgressDialog progressDialog;

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        protected String doInBackground(String... strings) {
            try {
                Services.addNewPersonalities(userId, "Love", quiz_result, ResultLove.this, new Services.TraitCallback() {
                    @Override
                    public void onSuccess(ArrayList<Trait> result) {
                        if(!result.isEmpty()){
                            Log.d("ResponsePersonalityType", String.valueOf(result.get(0).getPersonalityType()));
                            traitInfo=result;
                            //Log.d("Response result", String.valueOf(personalityInfo));
                            Log.d("Personality Update","Successful");

                            resultView.setText(quiz_result);
                            descView.setText(result.get(0).getDescription());
                            loveAvatar.setImageResource(getImageName(quiz_result));
                        }else{
                            Log.d("Else Response", "Multiple User Object Detected");
                        }
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
                resp = e.getMessage();
            }
            return resp;
        }


        @Override
        protected void onPostExecute(String result) {
            progressDialog.dismiss();
        }


        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(ResultLove.this, "",
                    "Loading. Please wait...", true);
        }


        @Override
        protected void onProgressUpdate(String... text) {

        }
    }
}
