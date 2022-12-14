package com.example.personalitytest;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.personalitytest.models.Personality;
import com.example.personalitytest.models.Question;
import com.example.personalitytest.models.Trait;
import com.example.personalitytest.models.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.Arrays;

public class Home extends AppCompatActivity {

    private String USER_INFORMATION;
    private ImageView backimage;
    private TextView connect, tests;
    private TextView name;
    BottomNavigationView bottomnavigation;

    private Integer userId;
    private String userName;
    private String userEmail;
    private String userPassword;
    private String userGender;
    private String userDOB;
    private byte[] userProfilePic;
    private Bundle bundle = new Bundle();
    private ArrayList<User> userInfo = new ArrayList<User>();
    private boolean firstStartup = true;

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

            bundle = getIntent().getExtras();
            //userInformation.getParcelableArrayList("userInfo");

            ArrayList<User> userInformationArray = new ArrayList<User>();
            userInformationArray = bundle.getParcelableArrayList("userInfo");


            userId = userInformationArray.get(0).getUserId();
            userName = userInformationArray.get(0).getName();
            userEmail = userInformationArray.get(0).getEmail();
            userPassword = userInformationArray.get(0).getPassword();
            userGender = userInformationArray.get(0).getGender();
            userDOB = userInformationArray.get(0).getDob();
            userProfilePic = userInformationArray.get(0).getProfilePic();

            Log.d("userName log", userName);
            Log.d("UserPicture log", String.valueOf(userProfilePic));

            homeFragment.setArguments(bundle);
            profileFragment.setArguments(bundle);
            connectFragment.setArguments(bundle);
        } else {
            Log.d("Error", "Bundle empty");

            SharedPreferences prefs = getSharedPreferences(USER_INFORMATION, MODE_PRIVATE);
            userId = prefs.getInt("userId", 0);
            userName = prefs.getString("name", "default");
            userEmail = prefs.getString("email", "default");
            userPassword = prefs.getString("password", "default");
            userGender = prefs.getString("gender", "default");
            userDOB = prefs.getString("DOB", "default");
            userProfilePic = Base64.decode(prefs.getString("profilePic", "default"), Base64.DEFAULT);

            Log.d("User_name", "User name, " + userName);
            Log.d("User_picture", "User picture, " + userProfilePic);

            userInfo.add(new User(userId, userName, userEmail, userPassword, userGender, userDOB, userProfilePic));
//
//            userInformation.putInt("userId", userId);
//            userInformation.putString("name", userName);
//            userInformation.putString("email", userEmail);
//            userInformation.putString("gender", userGender);
//            userInformation.putString("dob", userDOB);

            bundle.putParcelableArrayList("userInfo", userInfo);

            profileFragment.setArguments(bundle);
            homeFragment.setArguments(bundle);
            connectFragment.setArguments(bundle);
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onStart() {
        super.onStart();
        AsyncTaskRunner serviceTask = new AsyncTaskRunner();
        serviceTask.execute();
    }


    @Override
    protected void onResume() {
        super.onResume();
        AsyncTaskRunner serviceTask = new AsyncTaskRunner();
        serviceTask.execute();
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
        String profilePicString = Base64.encodeToString(userProfilePic, Base64.DEFAULT);
        editor.putString("profilePic", profilePicString);
        editor.apply();
    }



    @Override
    protected void onSaveInstanceState(@NonNull Bundle userInformation) {

        super.onSaveInstanceState(userInformation);

        userInformation.putInt("userId", userId);
        userInformation.putString("name", userName);
        userInformation.putString("email", userEmail);
        userInformation.putString("gender", userGender);
        userInformation.putString("dob", userDOB);
        userInformation.putByteArray("profilePic", userProfilePic);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle userInformation) {
        super.onRestoreInstanceState(userInformation);
        userId = userInformation.getInt("userId");
        userName = userInformation.getString("name");
        userEmail = userInformation.getString("email");
        userGender = userInformation.getString("gender");
        userDOB = userInformation.getString("DOB");

    }

    private void pdpaNotice(){
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        //Yes button clicked
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        finish();
                        System.exit(0);
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.pdpa_text)).setPositiveButton("Agree", dialogClickListener)
                .setNegativeButton("Disagree", dialogClickListener).setTitle("Data Privacy Disclaimer").show();
    }

    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        private String resp;
        ProgressDialog progressDialog;

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        protected String doInBackground(String... strings) {
            try {
                Services.getAllPersonalities(Home.this, new Services.PersonalityCallback(){
                    @Override
                    public void onSuccess(ArrayList<Personality> result) {
                        if(!result.isEmpty()){
                            bundle.putParcelableArrayList("personality_information",result);
                            //test
                            for(int i=0;i<result.size();i++){
                                //Log.d("test getAllPersonality", String.valueOf(result.get(i).getUserId()));
                            }
                        }else{
                            Log.d("Else Response", "Multiple User Object Detected");
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

                Services.getAllUsers(Home.this, new Services.UserCallback() {
                    @Override
                    public void onSuccess(ArrayList<User> result) {
                        if(!result.isEmpty()){
                            bundle.putParcelableArrayList("all_users",result);
                            //test
                            for(int i=0;i<result.size();i++){
                                //Log.d("test getAllUsers", String.valueOf(result.get(i).getUserId()));
                            }
                        }else{
                            Log.d("Else Response", "Multiple User Object Detected");

                        }
                    }

                    @Override
                    public void onFailure(String error) {
                        Log.d("FailRes GetUsers", error);
                    }
                });

                Services.getAllTraits(Home.this, new Services.TraitCallback() {
                    @Override
                    public void onSuccess(ArrayList<Trait> result) {
                        if(result.isEmpty()){
                            //Log.d("getAllTraits empty","");
                        }else{
                            bundle.putParcelableArrayList("traits4prof",result);
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
            } catch (Exception e) {
                e.printStackTrace();
                resp = e.getMessage();
            }
            return resp;
        }


        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        protected void onPostExecute(String result) {
            try {
                Thread.sleep(500);
                progressDialog.dismiss();
                if(firstStartup){
                    pdpaNotice();
                    firstStartup = false;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(Home.this, "",
                    "Loading. Please wait...", true);
        }


        @Override
        protected void onProgressUpdate(String... text) {

        }
    }
}

