package com.example.personalitytest;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.personalitytest.models.Personality;
import com.example.personalitytest.models.Trait;
import com.example.personalitytest.models.User;

import java.util.ArrayList;

public class hist_personality extends AppCompatActivity {

    private ImageView backimage;

    private TextView tvDate0,tvDate1,tvDate2,tvDate3,tvDate4,tvTrait0,tvTrait1,tvTrait2,tvTrait3,tvTrait4,tvDesc;
    private CardView cvTrait1,cvTrait2,cvTrait3,cvTrait4;

    private String USER_INFORMATION;
    private Integer userId;
    private String userName;
    private String userEmail;
    private String userGender;
    private String userDOB;
    private Bundle userInformation = new Bundle();
    private ArrayList<User> userInfo = new ArrayList<User>();
    private ArrayList<Personality> personalityAL = new ArrayList<Personality>();
    private ArrayList<String> dateTimeAL = new ArrayList<String>();
    private ArrayList<String> userIdAL = new ArrayList<String>();
    private ArrayList<String> qnCatAL = new ArrayList<String>();
    private ArrayList<String> persTypeAL = new ArrayList<String>();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hist_personality);


        getUserInfo();
        userId=userInfo.get(0).getUserId();
        userName=userInfo.get(0).getName();
        userEmail=userInfo.get(0).getEmail();
        userGender=userInfo.get(0).getGender();
        userDOB= userInfo.get(0).getDob();
        Log.d("User HC info test", String.valueOf(userId)+", "+userName);




        ProgressDialog dialog = ProgressDialog.show(hist_personality.this, "",
                "Loading. Please wait...", true);
        Services.getAllPersonalities(hist_personality.this, new Services.PersonalityCallback(){
            @Override
            public void onSuccess(ArrayList<Personality> result) {
                if(!result.isEmpty()){
                    //test
                    personalityAL=result;
//                    for(int i=0;i<result.size();i++){
//                        Log.d("histP_getAllPers", personalityAL.get(i).getUserId() +","+personalityAL.get(i).getDateTime()+", " +
//                                personalityAL.get(i).getQnCategory() +" "+ personalityAL.get(i).getPersonalityType());
//                    }

                }else{
                    Log.d("Else Response", "Multiple User Object Detected");
                }
                dialog.cancel();

                //declare
                tvDate0 = (TextView) findViewById(R.id.tvDate0);
                tvDate1 = (TextView) findViewById(R.id.tvDate1);
                tvDate2 = (TextView) findViewById(R.id.tvDate2);
                tvDate3 = (TextView) findViewById(R.id.tvDate3);
                tvDate4 = (TextView) findViewById(R.id.tvDate4);

                tvTrait0 = (TextView) findViewById(R.id.tvTrait0);
                tvTrait1 = (TextView) findViewById(R.id.tvTrait1);
                tvTrait2 = (TextView) findViewById(R.id.tvTrait2);
                tvTrait3 = (TextView) findViewById(R.id.tvTrait3);
                tvTrait4 = (TextView) findViewById(R.id.tvTrait4);

                cvTrait1 = (CardView) findViewById(R.id.cvTrait1);
                cvTrait2 = (CardView) findViewById(R.id.cvTrait2);
                cvTrait3 = (CardView) findViewById(R.id.cvTrait3);
                cvTrait4 = (CardView) findViewById(R.id.cvTrait4);

                tvDesc=(TextView) findViewById(R.id.tvDesc);


                //convert
                //check size
                Log.d("sizeAL", String.valueOf(personalityAL.size()));

                //filtering based on UserId and Quiz Category
                for(int i=0;i<personalityAL.size();i++){
                    if(personalityAL.get(i).getQnCategory().equals("16Personalities")&&personalityAL.get(i).getUserId()==userId) {
                        dateTimeAL.add(personalityAL.get(i).getDateTime());
                        userIdAL.add(String.valueOf(personalityAL.get(i).getUserId()));
                        qnCatAL.add(personalityAL.get(i).getQnCategory());
                        persTypeAL.add(personalityAL.get(i).getPersonalityType());
                    }
                }

                for(int x=0;x<dateTimeAL.size();x++){
                    Log.d("checkAL",dateTimeAL.get(x)+" "+userIdAL.get(x)+" "+qnCatAL.get(x));
                }


                if(dateTimeAL.size()==1){
                    String[] s0 = dateTimeAL.get(dateTimeAL.size()-1).split("T");
                    tvDate0.setText(s0[0]);
                    cvTrait1.setVisibility(View.INVISIBLE);
                    cvTrait2.setVisibility(View.INVISIBLE);
                    cvTrait3.setVisibility(View.INVISIBLE);
                    cvTrait4.setVisibility(View.INVISIBLE);
                    tvTrait0.setText(persTypeAL.get(persTypeAL.size()-1));
                }else if(dateTimeAL.size()==2){
                    String[] s0 = dateTimeAL.get(dateTimeAL.size()-1).split("T");
                    String[] s1 = dateTimeAL.get(dateTimeAL.size()-2).split("T");
                    tvDate0.setText(s0[0]);
                    tvDate1.setText(s1[0]);
                    cvTrait2.setVisibility(View.INVISIBLE);
                    cvTrait3.setVisibility(View.INVISIBLE);
                    cvTrait4.setVisibility(View.INVISIBLE);
                    tvTrait0.setText(persTypeAL.get(persTypeAL.size()-1));
                    tvTrait1.setText(persTypeAL.get(persTypeAL.size()-2));
                }else if(dateTimeAL.size()==3){
                    String[] s0 = dateTimeAL.get(dateTimeAL.size()-1).split("T");
                    String[] s1 = dateTimeAL.get(dateTimeAL.size()-2).split("T");
                    String[] s2 = dateTimeAL.get(dateTimeAL.size()-3).split("T");
                    tvDate0.setText(s0[0]);
                    tvDate1.setText(s1[0]);
                    tvDate2.setText(s2[0]);
                    cvTrait3.setVisibility(View.INVISIBLE);
                    cvTrait4.setVisibility(View.INVISIBLE);
                    tvTrait0.setText(persTypeAL.get(persTypeAL.size()-1));
                    tvTrait1.setText(persTypeAL.get(persTypeAL.size()-2));
                    tvTrait2.setText(persTypeAL.get(persTypeAL.size()-3));
                }else if(dateTimeAL.size()==4){
                    String[] s0 = dateTimeAL.get(dateTimeAL.size()-1).split("T");
                    String[] s1 = dateTimeAL.get(dateTimeAL.size()-2).split("T");
                    String[] s2 = dateTimeAL.get(dateTimeAL.size()-3).split("T");
                    String[] s3 = dateTimeAL.get(dateTimeAL.size()-4).split("T");
                    tvDate0.setText(s0[0]);
                    tvDate1.setText(s1[0]);
                    tvDate2.setText(s2[0]);
                    tvDate3.setText(s3[0]);
                    cvTrait4.setVisibility(View.INVISIBLE);
                    tvTrait0.setText(persTypeAL.get(persTypeAL.size()-1));
                    tvTrait1.setText(persTypeAL.get(persTypeAL.size()-2));
                    tvTrait2.setText(persTypeAL.get(persTypeAL.size()-3));
                    tvTrait3.setText(persTypeAL.get(persTypeAL.size()-4));
                }else{
                    String[] s0 = dateTimeAL.get(dateTimeAL.size()-1).split("T");
                    String[] s1 = dateTimeAL.get(dateTimeAL.size()-2).split("T");
                    String[] s2 = dateTimeAL.get(dateTimeAL.size()-3).split("T");
                    String[] s3 = dateTimeAL.get(dateTimeAL.size()-4).split("T");
                    String[] s4 = dateTimeAL.get(dateTimeAL.size()-5).split("T");
                    tvDate0.setText(s0[0]);
                    tvDate1.setText(s1[0]);
                    tvDate2.setText(s2[0]);
                    tvDate3.setText(s3[0]);
                    tvDate4.setText(s4[0]);
                    tvTrait0.setText(persTypeAL.get(persTypeAL.size()-1));
                    tvTrait1.setText(persTypeAL.get(persTypeAL.size()-2));
                    tvTrait2.setText(persTypeAL.get(persTypeAL.size()-3));
                    tvTrait3.setText(persTypeAL.get(persTypeAL.size()-4));
                    tvTrait4.setText(persTypeAL.get(persTypeAL.size()-5));
                }
            }
        });

        Services.getAllTraits(hist_personality.this, new Services.TraitCallback() {
            @Override
            public void onSuccess(ArrayList<Trait> result) {
                if(result.isEmpty()){
                    Log.d("getAllTraits empty","");
                }
                for(int i =0;i<result.size();i++){
                    if(tvTrait0.getText().equals(result.get(i).getPersonalityType())){
                        String temp = String.valueOf(tvTrait0.getText());
                        Log.d("testgetDesc",result.get(i).getDescription());
                        tvDesc.setText(result.get(i).getDescription());
                        tvTrait0.setText(result.get(i).getTraitName()+" ("+temp+")");
                    }
                }
            }
        });







        backimage = (ImageView) findViewById(R.id.personalitybacktoresults);
        backimage.setClickable(true);
        backimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().
                        replace(R.id.personalityHistoryPage,new profileFragment()).commit();

            }
        });
    }

    public void getUserInfo(){
        if (getIntent().getExtras() != null) {
            Log.d("Bundle log", "Bundle not empty");
            userInformation = getIntent().getExtras();
            userInfo = userInformation.getParcelableArrayList("userInfo");
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