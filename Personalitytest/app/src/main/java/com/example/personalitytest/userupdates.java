package com.example.personalitytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class userupdates extends AppCompatActivity {


    private ImageView backimage;
    private ImageView DPbtn;
    private TextView button;
    private TextView button2;
    private TextView button3;
    private TextView button4;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    TextView name, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userupdates);

        name = findViewById(R.id.username);
        email = findViewById(R.id.email);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            name.setText(personName);
            email.setText(personEmail);
        }

        backimage = (ImageView) findViewById(R.id.homeBtn);
        backimage.setClickable(true);
        backimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeactivity();
            }
        });

        DPbtn = (ImageView) findViewById(R.id.changeDPbtn);
        DPbtn.setClickable(true);
        DPbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeactivity();
            }
        });


        button = (TextView) findViewById(R.id.settingsBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tosettingsactivity();
            }
        });

        button2 = (TextView) findViewById(R.id.personalityHistory);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                topersonalityhistoryactivity();
            }
        });

        button3 = (TextView) findViewById(R.id.loveHistory);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tolovehistoryactivity();
            }
        });

        button4 = (TextView) findViewById(R.id.careerHistory);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tocareerhistoryactivity();
            }
        });



    }

    public void homeactivity() {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

    public void tosettingsactivity() {
        Intent intent = new Intent(this, usersettings.class);
        startActivity(intent);
    }

    public void topersonalityhistoryactivity() {
        Intent intent = new Intent(this, hist_personality.class);
        startActivity(intent);
    }

    public void tolovehistoryactivity() {
        Intent intent = new Intent(this, hist_love.class);
        startActivity(intent);
    }

    public void tocareerhistoryactivity() {
        Intent intent = new Intent(this, hist_career.class);
        startActivity(intent);
    }
    public void changeactivity() {
        Intent intent = new Intent(this, changeDP.class);
        startActivity(intent);
    }


}