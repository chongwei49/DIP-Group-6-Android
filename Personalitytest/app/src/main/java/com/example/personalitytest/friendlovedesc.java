package com.example.personalitytest;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.personalitytest.models.Trait;
import com.example.personalitytest.models.User;

import java.util.ArrayList;

public class friendlovedesc extends AppCompatActivity {
    private User recUser;

    private TextView username, email, personality, personalitydesc;

    private ImageView avatar;

    private ArrayList loveTraits;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friendlovedesc);

        if(getIntent()!=null){
            recUser=getIntent().getParcelableExtra("recUser");
            loveTraits=getIntent().getParcelableArrayListExtra("loveTraits");
            Log.d("name",recUser.getName());
            Log.d("trait", String.valueOf(loveTraits.get(loveTraits.size()-1)));

            //declare TextView
            username=findViewById(R.id.username);
            email=findViewById(R.id.email2);
            personality=findViewById(R.id.friends_loveresult); //change this
            personalitydesc=findViewById(R.id.description); //change in XML

            //setTexts
            username.setText(recUser.getName());
            email.setText(recUser.getEmail());
            personality.setText(String.valueOf(loveTraits.get(loveTraits.size()-1)));

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
            avatar=findViewById(R.id.love_avatar);

        }else{
            Log.d("intent","empty");
        }
    }
}