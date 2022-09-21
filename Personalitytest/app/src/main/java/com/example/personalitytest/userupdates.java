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
    private Button button;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    TextView name, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userupdates);

        name = findViewById(R.id.textView);
        email = findViewById(R.id.textView3);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            name.setText(personName);
            email.setText(personEmail);
        }

        backimage = (ImageView) findViewById(R.id.updatesback);
        backimage.setClickable(true);
        backimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatesbackactivity();
            }
        });

        button = (Button) findViewById(R.id.updatestochart);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tochartactivity();
            }
        });
        button = (Button) findViewById(R.id.updatestosettings);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tosettingsactivity();
            }
        });



    }

    public void updatesbackactivity() {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }
    public void tochartactivity() {
        Intent intent = new Intent(this, usercharts.class);
        startActivity(intent);
    }
    public void tosettingsactivity() {
        Intent intent = new Intent(this, usersettings.class);
        startActivity(intent);
    }
}