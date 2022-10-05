package com.example.personalitytest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Random;

import android.content.Intent;
import android.content.ReceiverCallNotAllowedException;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class connect_main extends AppCompatActivity {
    private RecyclerView personalityRecyclerView, loveRecyclerView, workRecyclerView;
    ArrayList<String> dataSource;
    MyPersonalityAdapter myPersonalityAdapter;
    MyLoveAdapter myLoveAdapter;
    MyWorkAdapter myWorkAdapter;
    private TextView connect, profile;
    private ImageView backimage;
    private Button personalitynext, lovenext, worknext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_main);


        personalityRecyclerView = findViewById(R.id.personalityRecyclerView);
        loveRecyclerView = findViewById(R.id.loveRecyclerView);
        workRecyclerView = findViewById(R.id.workRecyclerView);

        //Setting the data source
        dataSource = new ArrayList<>();
        dataSource.add("Sam");
        dataSource.add("Anthony");
        dataSource.add("Lucy");
        dataSource.add("Max");
        dataSource.add("Andy");

        myPersonalityAdapter = new MyPersonalityAdapter(dataSource);
        personalityRecyclerView.setLayoutManager(new LinearLayoutManager(connect_main.this, LinearLayoutManager.HORIZONTAL, false));
        personalityRecyclerView.setAdapter(myPersonalityAdapter);

        myLoveAdapter = new MyLoveAdapter(dataSource);
        loveRecyclerView.setLayoutManager(new LinearLayoutManager(connect_main.this, LinearLayoutManager.HORIZONTAL, false));
        loveRecyclerView.setAdapter(myLoveAdapter);

        myWorkAdapter = new MyWorkAdapter(dataSource);
        workRecyclerView.setLayoutManager(new LinearLayoutManager(connect_main.this, LinearLayoutManager.HORIZONTAL, false));
        workRecyclerView.setAdapter(myWorkAdapter);

        backimage = (ImageView) findViewById(R.id.homeBtn2);
        backimage.setClickable(true);
        backimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeactivity();
            }
        });

        connect = (TextView) findViewById(R.id.friendsBtn2);
        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toconnectactivity();
            }
        });

        profile = (TextView) findViewById(R.id.profileBtn2);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toprofileactivity();
            }
        });

        personalitynext = (Button) findViewById(R.id.personalitynext);
        personalitynext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                topersonalcompatibility();
            }
        });

        lovenext = (Button) findViewById(R.id.lovenext);
        lovenext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                topersonalcompatibility();
            }
        });

        worknext = (Button) findViewById(R.id.worknext);
        worknext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                topersonalcompatibility();
            }
        });
    }

    class MyPersonalityAdapter extends RecyclerView.Adapter<MyPersonalityAdapter.Personality>{
        ArrayList<String> data;
        public MyPersonalityAdapter(ArrayList<String> data) {
           this.data = data;
        }

        @NonNull
        @Override
        public Personality onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(connect_main.this).inflate(R.layout.accountprofile, parent, false);
            return new Personality(view);
        }

        @Override
        public void onBindViewHolder(@NonNull Personality holder, int position) {
            holder.name.setText(data.get(position));
        }

        @Override
        public int getItemCount() {
            return 4;
        }

        class Personality extends RecyclerView.ViewHolder{
            TextView name;
            public Personality(@NonNull View itemView){
                super(itemView);

                name = itemView.findViewById(R.id.name);
            }
        }
    }
    class MyLoveAdapter extends RecyclerView.Adapter<MyLoveAdapter.Love>{
        ArrayList<String> data;
        public MyLoveAdapter(ArrayList<String> data) {
            this.data = data;
        }

        @NonNull
        @Override
        public Love onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(connect_main.this).inflate(R.layout.accountprofile, parent, false);
            return new Love(view);
        }

        @Override
        public void onBindViewHolder(@NonNull Love holder, int position) {
            Random random = new Random();
            int x = random.nextInt(data.size());
            holder.name.setText(data.get(x));
        }

        @Override
        public int getItemCount() {
            return 4;
        }

        public class Love extends RecyclerView.ViewHolder{
            TextView name;
            public Love(@NonNull View itemView){
                super(itemView);

                name = itemView.findViewById(R.id.name);
            }
        }

    }
    class MyWorkAdapter extends RecyclerView.Adapter<MyWorkAdapter.Work>{
        ArrayList<String> data;
        public MyWorkAdapter(ArrayList<String> data) {
            this.data = data;
        }

        @NonNull
        @Override
        public Work onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(connect_main.this).inflate(R.layout.accountprofile, parent, false);
            return new Work(view);
        }

        @Override
        public void onBindViewHolder(@NonNull Work holder, int position) {
            Random random = new Random();
            int x = random.nextInt(data.size());
            holder.name.setText(data.get(x));
        }

        @Override
        public int getItemCount() {
            return 4;
        }

        public class Work extends RecyclerView.ViewHolder{
            TextView name;
            public Work(@NonNull View itemView){
                super(itemView);

                name = itemView.findViewById(R.id.name);
            }
        }

    }

    public void toconnectactivity() {
        Intent intent = new Intent(this, connect_main.class);
        startActivity(intent);
    }

    public void toprofileactivity() {
        Intent intent = new Intent(this, userupdates.class);
        startActivity(intent);
    }

    public void homeactivity() {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

    public void topersonalcompatibility() {
        Intent intent = new Intent(this, connect_person.class);
        startActivity(intent);
    }

}