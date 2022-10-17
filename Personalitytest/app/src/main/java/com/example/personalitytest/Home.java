package com.example.personalitytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Home extends AppCompatActivity {

    private ImageView backimage;
    private TextView connect, tests;
    private TextView name;
    BottomNavigationView bottomnavigation;

    homeFragment homeFragment = new homeFragment();
    connectFragment connectFragment = new connectFragment();
    profileFragment profileFragment = new profileFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

            bottomnavigation = findViewById(R.id.navigation);

            getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();

            bottomnavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected( MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.home:
                            getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();
                            return true;
                        case R.id.friends:
                            getSupportFragmentManager().beginTransaction().replace(R.id.container,connectFragment).commit();
                            return true;
                        case R.id.profile:
                            getSupportFragmentManager().beginTransaction().replace(R.id.container,profileFragment).commit();
                            return true;
                    }

                    return false;
                }
            });




    }




    public void homeactivity() {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

    public void totestsctivity() {
        Intent intent = new Intent(this, Tests.class);
        startActivity(intent);
    }
}