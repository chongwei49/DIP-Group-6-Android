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
        Services.getAllPersonalities(hist_career.this, new Services.PersonalityCallback() {
            @Override
            public void onSuccess(ArrayList<Personality> result) {
                personalityAL=result;
                dialog.cancel();
            }
        });

        //compare datetimes and sort by most recent
        for(int i=0;i<personalityAL.size();i++){
//            Log.d("ALL TYPES","personalityType: "+personalityAL.get(i).getPersonalityType()+"dateTime:"+personalityAL.get(i).getDateTime());
            if(personalityAL.get(i).getQnCategory()=="Quiz"){
                Log.d("check","personalityType: "+personalityAL.get(i).getPersonalityType()+"dateTime:"+personalityAL.get(i).getDateTime());
            }else{
                Log.d("error","no match or no such category");
            }
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