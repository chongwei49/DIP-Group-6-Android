package com.example.personalitytest;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.personalitytest.models.Question;
import com.example.personalitytest.models.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    private String USER_INFORMATION;
    private ImageView backimage;
    private TextView connect, tests;
    private TextView name;
    BottomNavigationView bottomnavigation;

    private Integer userId;
    private String userName;
    private String userEmail;
    private String userGender;
    private String userDOB;
    private Bundle userInformation = new Bundle();
    private ArrayList<User> userInf = new ArrayList<User>();

    homeFragment homeFragment = new homeFragment();
    connectFragment connectFragment = new connectFragment();
    profileFragment profileFragment = new profileFragment();

    public Home() {
        super();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

            bottomnavigation = findViewById(R.id.navigation);

            getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();


        if (getIntent().getExtras() != null) {
            Log.d("Bundle log", "Bundle not empty");

            userInformation = getIntent().getExtras();

            userId = userInformation.getInt("userId");
            userName = userInformation.getString("name");
            userEmail = userInformation.getString("email");
            userGender = userInformation.getString("gender");
            userDOB = userInformation.getString("dob");

            homeFragment.setArguments(userInformation);
            profileFragment.setArguments(userInformation);
            connectFragment.setArguments(userInformation);
        } else {
            Log.d("Error", "Bundle empty");

            SharedPreferences prefs = getSharedPreferences(USER_INFORMATION, MODE_PRIVATE);
            userId = prefs.getInt("userId", 0);
            userName = prefs.getString("name", "default");
            userEmail = prefs.getString("email", "default");
            userGender = prefs.getString("gender", "default");
            userDOB = prefs.getString("DOB", "default");

            Log.d("User name", "User Name, " + userName);

            userInformation.putInt("userId", userId);
            userInformation.putString("name", userName);
            userInformation.putString("email", userEmail);
            userInformation.putString("gender", userGender);
            userInformation.putString("dob", userDOB);

            profileFragment.setArguments(userInformation);
            homeFragment.setArguments(userInformation);
            connectFragment.setArguments(userInformation);
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

        //getAllUsers

        ProgressDialog dialog = ProgressDialog.show(Home.this, "",
                "Loading. Please wait...", true);
        Services.getAllUsers( Home.this, new Services.UserCallback() {
            @Override
            public void onSuccess(ArrayList<User> result) {
                Log.d("Response result", String.valueOf(result.get(0).getName()));
                dialog.cancel();
                if(!result.isEmpty()){
                    userInf=result;
                    Bundle usersInfo = new Bundle();
                    usersInfo.putParcelableArrayList("user_information",userInf);
                    connectFragment.setArguments(usersInfo);

                    //test
                    for(int i=0;i<result.size();i++){
                        Log.d("test getAllUsers",result.get(i).getName());
                    }
                }else{
                    Log.d("Else Response", "Multiple User Object Detected");
                }
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = getSharedPreferences(
                USER_INFORMATION, MODE_PRIVATE).edit();
        editor.putInt("userId", userId);
        editor.putString("name", userName);
        editor.putString("email", userEmail);
        editor.putString("gender", userGender);
        editor.putString("dob", userDOB);
        editor.apply();
    }



//    @Override
//    protected void onSaveInstanceState(@NonNull Bundle userInformation) {
//
//        super.onSaveInstanceState(userInformation);
//
//        userInformation.putInt("userId", userId);
//        userInformation.putString("name", userName);
//        userInformation.putString("email", userEmail);
//        userInformation.putString("gender", userGender);
//        userInformation.putString("dob", userDOB);
//
//    }
//
//    @Override
//    protected void onRestoreInstanceState(@NonNull Bundle userInformation) {
//        super.onRestoreInstanceState(userInformation);
//        userId = userInformation.getInt("userId");
//        userName = userInformation.getString("name");
//        userEmail = userInformation.getString("email");
//        userGender = userInformation.getString("gender");
//        userDOB = userInformation.getString("DOB");
//
//
//    }

}