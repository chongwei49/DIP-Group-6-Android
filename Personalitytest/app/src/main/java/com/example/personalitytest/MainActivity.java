package com.example.personalitytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    VideoView videoView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.googleBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailactivity();
            }
        });


        videoView = findViewById(R.id.seaVid);
        Uri uri=Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.costar);
        videoView.setVideoURI(uri);
        videoView.start();

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true);
            }
        });
    }

    public void emailactivity() {
        Intent intent = new Intent(this, Email.class);
        startActivity(intent);
    }

    public void birthplaceactivity() {
        Intent intent = new Intent(this, BirthPlace.class);
        startActivity(intent);
    }

    @Override
    protected void onPostResume(){
        videoView.resume();
        super.onPostResume();
    }
    @Override
    protected void onRestart() {
        videoView.start();
        super.onRestart();
    }

    @Override
    protected void onPause(){
        videoView.suspend();
        super.onPause();
    }
}