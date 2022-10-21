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

public class ResultPersonality extends AppCompatActivity {
    private ImageView homebutton;
    private TextView resultView, descView;
    private String quiz_result, description="";
    private ArrayList<String> preCalc = new ArrayList<String>();
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultpersonality);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("BUNDLE");
        preCalc= (ArrayList<String>) bundle.getSerializable("preCalc_values");

        for(int x=0;x<preCalc.size();x++){
            if(preCalc.isEmpty()){
                Log.d("Empty bundle (Results)",preCalc.get(x));
            }else{
                Log.d("Not Empty",preCalc.get(x));
            }
        }

        ProgressDialog dialog = ProgressDialog.show(ResultPersonality.this, "",
                "Loading. Please wait...", true);
        Services.getAllTraits(ResultPersonality.this, new Services.TraitCallback() {
            @Override
            public void onSuccess(ArrayList<Trait> result) {
                for(int i=0; i<result.size(); i++){
                    Log.d("Trait Names", result.get(i).getTraitName());
                    if(result.get(i).getTraitName().contains(quiz_result)){
                        description = result.get(i).getDescription();
                    }
                }
                //resultView.setText(quiz_result);
                //descView.setText(description);
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
    }
    public void tohomeactivity() {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

}
