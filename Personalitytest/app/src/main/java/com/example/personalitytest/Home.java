package com.example.personalitytest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

    private String userId;
    private String userName;
    private String userEmail;
    private String userGender;
    private String userDOB;

    homeFragment homeFragment = new homeFragment();
    connectFragment connectFragment = new connectFragment();
    profileFragment profileFragment = new profileFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

            bottomnavigation = findViewById(R.id.navigation);

            getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();

            if (getIntent().getExtras() != null) {
                Bundle userInformation = getIntent().getExtras();
                userId = userInformation.getString("userId");
                userName = userInformation.getString("name");
                userEmail = userInformation.getString("email");
                userGender = userInformation.getString("gender");
                userDOB = userInformation.getString("DOB");

                homeFragment.setArguments(userInformation);

            } else {
                Log.e("Error", "Bundle empty");
                Log.d("User name", "User Name, " +  userName);

                Bundle userInformation = new Bundle();
                userInformation.putString("userId", userId);
                userInformation.putString("name", userName);
                userInformation.putString("email", userEmail);
                userInformation.putString("gender", userGender);
                userInformation.putString("dob", userDOB);

                homeFragment.setArguments(userInformation);
            }

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
        this.finish();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle userInformation) {

        super.onSaveInstanceState(userInformation);

        userInformation.putString("userId", userId);
        userInformation.putString("name", userName);
        userInformation.putString("email", userEmail);
        userInformation.putString("gender", userGender);
        userInformation.putString("dob", userDOB);

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle userInformation) {
        super.onRestoreInstanceState(userInformation);
        userId = userInformation.getString("userId");
        userName = userInformation.getString("name");
        userEmail = userInformation.getString("email");
        userGender = userInformation.getString("gender");
        userDOB = userInformation.getString("DOB");


    }

}