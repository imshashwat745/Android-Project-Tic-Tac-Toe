package com.example.imshashwat745.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class NameCollect extends AppCompatActivity  {
    EditText p1;
    EditText p2;
    String iP;
    ImageView imageView;
    MediaPlayer mediaPlayer;
    MainActivity ob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_collect);
        p1=findViewById(R.id.player1);
        p2=findViewById(R.id.player2);
        p1.setText("");
        p2.setText("");

    }
    /*p1-player 1
    np2-name of player 2
    */
    public void startGame(View v){
        String np1="",np2="";
        np1=p1.getText().toString();
        np2=p2.getText().toString();
        if(np1.equals("")&&np2.equals("")) {
            np1="Player1";
            np2="Player2";
    }
        else if(np1.equals("")){
            np1="Player1";
        }
        else if(np2.equals("")){
        np2="Player2";
     }

        else{
            if(np1.equals(np2)){
                np2=np2+"(1)";
            }
        }
        Intent it=new Intent(this,game.class);
        it.putExtra("com.NameCollect.Player1",np1);
        it.putExtra("com.NameCollect.Player2",np2);
        startActivity(it);
}
}