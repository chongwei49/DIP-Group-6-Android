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
    private TextView resultView, descView;
    private String quiz_result, description="";
    private ArrayList<Question> preCalc = new ArrayList<Question>();
    HashMap<String, String> order = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultpersonality);

        order = initOrderHashMap(order);

        resultView = findViewById(R.id.textView8);
        descView = findViewById(R.id.textView10);

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


                for (Map.Entry<String, Integer> en : hm1.entrySet()) {
                    System.out.println("Key = " + en.getKey() +
                            ", Value = " + en.getValue());
                }

                resultView.setText(formulatePType(hm1));
                //descView.setText(description);
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
        for (Map.Entry<String, Integer> entry1 : input.entrySet()) {
            for (Map.Entry<String, String> entry2 : order.entrySet()) {
                if(entry1.getKey().equals(entry2.getKey()) && result.length()!=4){
                    Log.d("Trait_Test", entry2.getValue());
                    result = result + entry2.getValue();
                }
            }
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
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // put data from sorted list to hashmap
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }

        return temp;
    }

    public HashMap<String, String> initOrderHashMap(HashMap<String, String> tmp){
        tmp.put("Introvert", "I");
        tmp.put("Extrovert", "E");

        tmp.put("Observant", "O");
        tmp.put("Intuitive", "N");

        tmp.put("Thinking", "T");
        tmp.put("Feeling", "F");

        tmp.put("Judging", "J");
        tmp.put("Prospecting", "P");


        return tmp;
    }

}
