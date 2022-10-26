package com.example.personalitytest;

import android.app.ProgressDialog;
import android.content.Intent;
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

public class ResultLove extends AppCompatActivity {
    private ImageView homebutton;
    private TextView resultView, descView;
    private String quiz_result, description="";
    private ImageView loveAvatar;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultlove);

        Intent intent = getIntent();
        quiz_result = intent.getStringExtra("Result");
        ProgressDialog dialog = ProgressDialog.show(ResultLove.this, "",
                "Loading. Please wait...", true);
        Services.getAllTraits(ResultLove.this, new Services.TraitCallback() {
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
                loveAvatar.setImageResource(getImageName(quiz_result));
                dialog.cancel();
            }
        });

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
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
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


}
