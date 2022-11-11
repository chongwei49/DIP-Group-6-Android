package com.example.personalitytest;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.personalitytest.models.Trait;
import com.example.personalitytest.models.User;

import java.util.ArrayList;

public class friendpersonalitydesc extends AppCompatActivity {
    private User recUser;

    private TextView username, email, personality, personalitydesc;

    private ImageView avatar;

    private ArrayList personalityTraits;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friendpersonalitydesc);
        if(getIntent()!=null){
            recUser=getIntent().getParcelableExtra("recUser");
            personalityTraits=getIntent().getParcelableArrayListExtra("personalityTraits");
            Log.d("name",recUser.getName());
            Log.d("trait", String.valueOf(personalityTraits.get(personalityTraits.size()-1)));

            //declare TextView
            username=findViewById(R.id.username);
            email=findViewById(R.id.email2);
            personality=findViewById(R.id.friends_personalityresult); //change this
            personalitydesc=findViewById(R.id.description); //change in XML

            //setTexts
            username.setText(recUser.getName());
            email.setText(recUser.getEmail());
            personality.setText(String.valueOf(personalityTraits.get(personalityTraits.size()-1)));

            //run getAllTraits service
            Services.getAllTraits(this, new Services.TraitCallback() {
                @Override
                public void onSuccess(ArrayList<Trait> result) {
                    if(result.isEmpty()){
                        Log.d("getAllTraits empty","");
                    }
                    //bundle.putParcelableArrayList("traitsList",result);
                    for(int i =0;i<result.size();i++){
                        if(personality.getText().equals(result.get(i).getPersonalityType())){
                            personalitydesc.setText(result.get(i).getDescription());
                            String temp = String.valueOf(personality.getText());
                            personality.setText(result.get(i).getTraitName()+" ("+temp+")");
                        }
                    }
                }
                @Override
                public void onFailure(String error) {
                    Context context = getApplicationContext();
                    CharSequence text = error;
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            });

            //setImage
            avatar=findViewById(R.id.personality_avatar);


        }else{
            Log.d("intent","empty");
        }
    }
}