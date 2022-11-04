package com.example.personalitytest;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.personalitytest.models.Personality;
import com.example.personalitytest.models.Trait;
import com.example.personalitytest.models.User;

import java.util.ArrayList;

public class hist_career extends AppCompatActivity {
    private ArrayList<Personality> personalityAL = new ArrayList<Personality>();
    private ImageView backimage;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hist_career);

        //getData

        ProgressDialog dialog = ProgressDialog.show(hist_career.this, "",
                "Loading. Please wait...", true);
        Services.getAllPersonalities(hist_career.this, new Services.PersonalityCallback(){
            @Override
            public void onSuccess(ArrayList<Personality> result) {
                personalityAL=result;
                if(!result.isEmpty()){
                    //test
                    for(int i=0;i<result.size();i++){
                        Log.d("test getAllPersonality", result.get(i).getUserId() +" "+result.get(i).getDateTime());
                    }
                    dialog.cancel();
                }else{
                    Log.d("Else Response", "Multiple User Object Detected");
                    dialog.cancel();
                }
            }
        });
//
//        String[] parts = personalityAL.get(0).getDateTime().split("T");
        Log.d("priv AL size", String.valueOf(personalityAL.size()));
        for(int i=0;i<personalityAL.size();i++){
            Log.d("splitTest",personalityAL.get(i).getDateTime());
        }



        backimage = (ImageView) findViewById(R.id.careerbacktoresults);
        backimage.setClickable(true);
        backimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().
                        replace(R.id.careerHistoryPage,new profileFragment()).commit();
            }
        });

    }

}