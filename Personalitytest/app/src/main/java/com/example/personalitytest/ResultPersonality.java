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

import com.example.personalitytest.models.Question;
import com.example.personalitytest.models.Trait;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ResultPersonality extends AppCompatActivity implements Serializable {
    private ImageView homebutton;
    private TextView resultView, descView, traitnameView;
    private String quiz_result, description="";
    private ArrayList<Question> preCalc = new ArrayList<Question>();
    String[][] orderList = {
            {"Introvert", "Extrovert"},
            {"Observant", "Intuitive"},
            {"Thinking", "Feeling"},
            {"Judging", "Prospecting"}
    };
    String[][] orderName = {
            {"I", "E"},
            {"O", "N"},
            {"T", "F"},
            {"J", "P"}

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultpersonality);

        resultView = findViewById(R.id.textView8);
        descView = findViewById(R.id.textView10);
        traitnameView = findViewById(R.id.textView9);

        homebutton = (ImageView) findViewById(R.id.homebutton);
        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tohomeactivity();
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        preCalc= intent.getParcelableArrayListExtra("questionAnswers");

        for(int x=0;x<preCalc.size();x++){
            Log.d("get array list: ",x+"QID: "+preCalc.get(x).getQnId()+" Ans:"+preCalc.get(x).getAnswer());
        }

        ProgressDialog dialog = ProgressDialog.show(ResultPersonality.this, "",
                "Loading. Please wait...", true);
        Services.getAllTraits(ResultPersonality.this, new Services.TraitCallback() {
            @Override
            public void onSuccess(ArrayList<Trait> result) {
                for(int i=0; i<result.size(); i++){
                    Log.d("Trait Names", result.get(i).getTraitName());
                }

                Map<String, Integer> hm1 = calculateResult(preCalc);
                String persoanlity_Type = formulatePType(hm1);
                String personality_TraitName = "";
                String persoanlity_Desc = "";

                for(int i=0; i<result.size();i++){
                    Log.d("personType", persoanlity_Type);
                    if(result.get(i).getPersonalityType().contains(persoanlity_Type)){
                        persoanlity_Desc = result.get(i).getDescription();
                        personality_TraitName = result.get(i).getTraitName();
                    }
                    Log.d("Trait_Checking", result.get(i).getPersonalityType());
                    Log.d("Key_Check", "---------------------------------");
                }

                resultView.setText(persoanlity_Type);
                descView.setText(persoanlity_Desc);
                traitnameView.setText(personality_TraitName);
                dialog.cancel();
            }
        });
    }

    public void tohomeactivity() {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

    public String formulatePType(Map<String, Integer> input){
        String result = "";
        String[] tmp = new String[4];
        int counter = 0;
        for (Map.Entry<String, Integer> entry1 : input.entrySet()) {
            if(counter<4){
                for(int i=0;i<orderList.length;i++){
//                    Log.d("Key_Check", entry1.getKey());
//                    Log.d("Key_Check", orderList[i][0]);
//                    Log.d("Key_Check", orderList[i][1]);
//                    Log.d("Key_Check", "Index: "+i);
                    if(entry1.getKey().equals(orderList[i][0])){
                        tmp[i] = orderName[i][0];
                        counter++;
                        break;
                    }
                    else if(entry1.getKey().equals(orderList[i][1])){
                        tmp[i] = orderName[i][1];
                        counter++;
                        break;
                    }
                }
//                Log.d("Key_Check", "---------------------------------");
            }

        }

        for(int i=0; i<tmp.length; i++){
            result = result + tmp[i];
        }

        return result;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public HashMap<String, Integer> calculateResult(ArrayList<Question> tmp){
        HashMap<String, Integer> resultMap = new HashMap<>();
        for(int i=0; i<tmp.size(); i++){
            if(!resultMap.containsKey(tmp.get(i).getTraits())){
                resultMap.put(tmp.get(i).getTraits(), 1);
            }else{
                resultMap.merge(tmp.get(i).getTraits(), 1, Integer::sum);
            }
        }

        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer> > list =
                new LinkedList<Map.Entry<String, Integer> >(resultMap.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2)
            {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        // put data from sorted list to hashmap
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }

        return temp;
    }




}
