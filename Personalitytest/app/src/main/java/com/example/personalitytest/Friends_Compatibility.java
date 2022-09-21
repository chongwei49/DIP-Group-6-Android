package com.example.personalitytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;

public class Friends_Compatibility extends AppCompatActivity {

    private ImageView button_back;
    private Button button_updates,button_chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_compatibility);

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        TextView textViewDate = findViewById(R.id.Date);
        textViewDate.setText(currentDate);

        button_back = (ImageView) findViewById(R.id.backBtn);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tohomeactivity();
            }
        });

        button_updates = (Button) findViewById(R.id.updatesBtn);
        button_updates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tofriendsupdatesactivity();
            }
        });

        button_chart = (Button) findViewById(R.id.chartBtn);
        button_chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tofriendschartactivity();
            }
        });
    }

    public void tohomeactivity() {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

    public void tofriendsupdatesactivity() {
        Intent intent = new Intent(this, Friends_Update.class);
        startActivity(intent);
    }

    public void tofriendschartactivity() {
        Intent intent = new Intent(this, Friends_Chart.class);
        startActivity(intent);
    }
}