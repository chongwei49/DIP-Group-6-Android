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

        button = findViewById(R.id.nextbtn);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                emailactivity();
            }

        });

        public void emailacitivty() {

        }

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