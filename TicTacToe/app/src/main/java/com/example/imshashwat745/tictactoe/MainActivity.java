package com.example.imshashwat745.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    public MediaPlayer mediaPlayer;
    ImageView imageView;
    String isP="t";
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer = MediaPlayer.create(this, R.raw.song);
        mediaPlayer.setLooping(true);
        mediaPlayer.setVolume(95,95);
        mediaPlayer.start(); // no need to call prepare(); create() does that for you
        imageView=findViewById(R.id.speaker);
        sp=getSharedPreferences("password",MODE_PRIVATE);
    }

    @Override
    protected void onDestroy() {
        mediaPlayer.stop();
    }

    public void speakerClick(View v){
        if(mediaPlayer.isPlaying()==true){
            mediaPlayer.stop();
            isP="f";
            imageView.setImageResource(R.drawable.mute);
        }
        else{
            mediaPlayer = MediaPlayer.create(this, R.raw.song);
            mediaPlayer.setLooping(true);
            mediaPlayer.setVolume(95,95);
            mediaPlayer.start();
            isP="t";
            imageView.setImageResource(R.drawable.sound);
        }
    }
    public void feedback(View v){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, "sks.comp2001@gmail.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, "FEEDBACK of TicTacToe");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(Intent.createChooser(intent,"Choose a client"));
        }
    }
    public void exit(View v){
        mediaPlayer.stop();
        finish();
        System.exit(0);
    }
    public void start(View v){
        Intent i=new Intent(this,choose.class);
        mediaPlayer.stop();
        startActivity(i);
    }
}