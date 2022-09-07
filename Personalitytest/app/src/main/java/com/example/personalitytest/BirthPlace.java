package com.example.personalitytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class BirthPlace extends AppCompatActivity {

    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birth_place);

        ListView list = (ListView) findViewById(R.id.countryList);
        EditText search = (EditText) findViewById(R.id.birthPlaceInput);

        ArrayList<String> country = new ArrayList<>();
        country.add("Afghanistan");
        country.add("Argentina");
        country.add("Belgium");
        country.add("Brazil");
        country.add("Brunei");
        country.add("Cambodia");
        country.add("China");
        country.add("France");
        country.add("Germany");
        country.add("Hong Kong");
        country.add("India");
        country.add("Iran");
        country.add("Italy");
        country.add("Japan");
        country.add("Malaysia");
        country.add("Mexico");
        country.add("Philippines");
        country.add("Russia");
        country.add("South Korea");
        country.add("Singapore");
        country.add("Taiwan");
        country.add("Thailand");
        country.add("United Kingdom");

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, country);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                homeactivity();
            }
        });

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    public void homeactivity(){
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }
}