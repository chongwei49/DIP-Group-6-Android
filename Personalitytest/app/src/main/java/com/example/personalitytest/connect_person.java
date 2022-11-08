package com.example.personalitytest;

import android.annotation.SuppressLint;
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
import androidx.cardview.widget.CardView;

import com.example.personalitytest.models.Personality;
import com.example.personalitytest.models.Trait;
import com.example.personalitytest.models.User;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class connect_person extends AppCompatActivity {

    private TextView connect, username, age;
    private Integer userId;
    private String userName;
    private String userEmail;
    private String userGender;
    private String userDOB;
    private byte[] userProfilePic;
    private ImageView backimage,profilepic;
    private Button notdone_personaltystart, notdone_lovestart, notdone_careerstart;
    private Boolean personality_16 = false, personality_love = false, personality_job = false;
    private CardView notdone_personalty, notdone_love, notdone_career;
    private TextView persTrait, loveTrait,careerTrait;


    private ArrayList<User> userInfo = new ArrayList<User>();
    private ArrayList<Personality> personalityList = new ArrayList<Personality>();
    private ArrayList<Trait> traitsList = new ArrayList<Trait>();
    private ArrayList<String> personalityTraits = new ArrayList<String>();
    private ArrayList<String> loveTraits = new ArrayList<String>();
    private ArrayList<String> careerTraits = new ArrayList<String>();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_person);

        String name = getIntent().getStringExtra("Name");
        String ages = getIntent().getStringExtra("Age");
        int image = getIntent().getIntExtra("Profile Pic",0);

        profilepic =  findViewById(R.id.profilepic);
        username = findViewById(R.id.username);
        age = findViewById(R.id.age);

        username.setText(name);
        age.setText(ages);
        profilepic.setImageResource(image);
        //set latest history results
        ProgressDialog dialog = ProgressDialog.show(connect_person.this, "",
                "Loading. Please wait...", true);
        Services.getAllPersonalities(connect_person.this, new Services.PersonalityCallback(){
            @Override
            public void onSuccess(ArrayList<Personality> result) {
                if(!result.isEmpty()){
                    //test
                    personalityList=result;
                    for(int i=0;i<result.size();i++){
                        Log.d("histC_getAllPers", personalityList.get(i).getUserId() +","+personalityList.get(i).getDateTime()+", " +
                                personalityList.get(i).getQnCategory());
                    }

                }else{
                    Log.d("Else Response", "Multiple User Object Detected");
                }
                dialog.cancel();


                //filtering based on UserId and Quiz Category

                for(int i =0;i<personalityList.size();i++){
                    //Log.d("test", personalityList.get(i).getPersonalityType());
                    if(personalityList.get(i).getQnCategory().contains("16Personalities")){
                        if (personalityList.get(i).getUserId() == userId) {
                            personalityTraits.add(personalityList.get(i).getPersonalityType());
                            personality_16 = true;
                        }
                    }
                    else if(personalityList.get(i).getQnCategory().contains("Love")){
                        if (personalityList.get(i).getUserId() == userId) {
                            loveTraits.add(personalityList.get(i).getPersonalityType());
                            personality_love = true;
                        }
                    }
                    else if(personalityList.get(i).getQnCategory().contains("Job")){
                        if (personalityList.get(i).getUserId() == userId) {
                            careerTraits.add(personalityList.get(i).getPersonalityType());
                            personality_job = true;
                        }
                    }
                    Log.d("ConFragment Per_List:",personalityList.get(i).getUserId().toString());
                }



            }
        });

        persTrait = (TextView) findViewById(R.id.persTrait);
        loveTrait = (TextView) findViewById(R.id.loveTrait);
        careerTrait = (TextView) findViewById(R.id.careerTrait);
        for(int i=0;i<personalityTraits.size();i++){
            Log.d("persTrait",personalityTraits.get(i));
        }
        persTrait.setText(personalityTraits.get(personalityTraits.size()-1));
        loveTrait.setText(loveTraits.get(loveTraits.size()-1));
        careerTrait.setText(careerTraits.get(careerTraits.size()-1));

        //edit text to include trait
        Services.getAllTraits(connect_person.this, new Services.TraitCallback() {
            @Override
            public void onSuccess(ArrayList<Trait> result) {
                traitsList=result;
                if(result.isEmpty()){
                    Log.d("getAllTraits empty","");
                }else{
                    for(int i =0;i<traitsList.size();i++){
                        if(persTrait.getText().equals(traitsList.get(i).getPersonalityType())){
                            String temp = String.valueOf(persTrait.getText());
                            Log.d("testgetDesc",traitsList.get(i).getDescription());
                            persTrait.setText(traitsList.get(i).getTraitName()+" ("+temp+")");
                        }
                    }
                }
            }
        });






        //

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
        setUpCardView();

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

    public void setUpCardView(){
        Log.d("16Personality check: ", personality_16.toString());
        Log.d("LovePersonality check: ", personality_love.toString());
        Log.d("JobPersonality check: ", personality_job.toString());
        if (personality_16) {
            notdone_personalty.setVisibility(View.GONE);
        }else{
            notdone_personalty.setVisibility(View.VISIBLE);
        }
        if (personality_love) {
            notdone_love.setVisibility(View.GONE);
        }else{
            notdone_love.setVisibility(View.VISIBLE);
        }
        if (personality_job) {
            notdone_career.setVisibility(View.GONE);
        }else{
            notdone_career.setVisibility(View.VISIBLE);
        }
    }

}
