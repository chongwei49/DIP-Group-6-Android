package com.example.personalitytest;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.personalitytest.models.User;

import java.util.ArrayList;

public class Signup extends AppCompatActivity {

    private ImageView backimage;
    private Button button;
    private TextView nameText, emailText, passText, dobText, genderText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        emailText = findViewById(R.id.emailInput);
        passText = findViewById(R.id.passwordInput);
        nameText = findViewById(R.id.nameInput);
        dobText = findViewById(R.id.dobInput);
        genderText = findViewById(R.id.genderInput);

        View decorView = getWindow().getDecorView();
        // Hide both the navigation bar and the status bar.
        // SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
        // a general rule, you should design your app to hide the status bar whenever you
        // hide the navigation bar.

        backimage = (ImageView) findViewById(R.id.signUpBck);
        backimage.setClickable(true);
        backimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainactivity();
            }
        });

        button = (Button) findViewById(R.id.signupBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                ProgressDialog dialog = ProgressDialog.show(Signup.this, "",
                        "Loading. Please wait...", true);
                Services.signUp(nameText.getText().toString(), emailText.getText().toString(), passText.getText().toString(), dobText.getText().toString(), genderText.getText().toString(),
                        Signup.this, new Services.UserCallback() {
                    @Override
                    public void onSuccess(ArrayList<User> result) {
                        Log.d("Response result", String.valueOf(result.get(0).getName()));
                        dialog.cancel();
                        if(!result.isEmpty()){
                            Bundle userInformation = new Bundle();
                            userInformation.putInt("userId", result.get(0).getUserId());
                            userInformation.putString("name", result.get(0).getName());
                            userInformation.putString("email", result.get(0).getEmail());
                            userInformation.putString("gender", result.get(0).getGender());
                            userInformation.putString("dob", result.get(0).getDob());
                            homeactivity(userInformation);
                            Log.d("userId Check", result.get(0).getUserId().toString());
                        }else{
                            Log.d("Else Response", "Multiple User Object Detected");
                        }
                    }
                });
                //homeactivity();
            }
        });
    }

    public void mainactivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void homeactivity(Bundle bundle) {
        Intent intent = new Intent(this, Home.class);
        intent.putExtras(bundle);
        startActivity(intent);
        this.finish();
    }
}