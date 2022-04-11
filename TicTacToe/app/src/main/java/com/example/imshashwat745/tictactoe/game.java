package com.example.imshashwat745.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class game extends AppCompatActivity {
    ImageView img;
    TextView turn;
    TextView result;
    MediaPlayer mp;
    int arr[]=new int[9];
    int Turn=1;
    int count=0;
    boolean win=false;
    database g;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        img=findViewById(R.id.g1);
        img.setImageResource(0);
        img=findViewById(R.id.g2);
        img.setImageResource(0);
        img=findViewById(R.id.g3);
        img.setImageResource(0);
        img=findViewById(R.id.g4);
        img.setImageResource(0);
        img=findViewById(R.id.g5);
        img.setImageResource(0);
        img=findViewById(R.id.g6);
        img.setImageResource(0);
        img=findViewById(R.id.g7);
        img.setImageResource(0);
        img=findViewById(R.id.g8);
        img.setImageResource(0);
        img=findViewById(R.id.g9);
        img.setImageResource(0);
        turn=findViewById(R.id.turn);
        result=findViewById(R.id.result);
        turn.setText(getIntent().getExtras().getString("com.NameCollect.Player1")+"'s Turn");
        for(int i=0;i<9;i++)
            arr[i]=-1;
    }

    public void set(View v){
        mp= MediaPlayer.create(this, R.raw.tap);
        img=findViewById(v.getId());
        if(img.getDrawable()==null&&win==false){
            count++;
            mp.start();
            if(Turn==1){
                Turn=0;
                img.setImageResource(R.drawable.x);
            }
            else{
                Turn=1;
                img.setImageResource(R.drawable.o);
            }
            check();
        }
        else{
            if(win==false)
            Toast.makeText(this, "Can't set here,already there ", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "Game is over Tap replay button to play again", Toast.LENGTH_SHORT).show();
        }
        if(count<9&&!win){
            if(Turn==1)
                turn.setText(getIntent().getExtras().getString("com.NameCollect.Player1")+"'s Turn");
            else
                turn.setText(getIntent().getExtras().getString("com.NameCollect.Player2")+"'s Turn");
        }
        else{
            turn.setText("Game Over!!");
        }
    }
    public void check(){
        String pl1=getIntent().getExtras().getString("com.NameCollect.Player1");
        String pl2=getIntent().getExtras().getString("com.NameCollect.Player2");
        boolean res=false;
        g=new database(this);
        switch(img.getId()){
            case R.id.g1:
                arr[0]=Turn;
                break;
            case R.id.g2:
                arr[1]=Turn;
                break;
            case R.id.g3:
                arr[2]=Turn;
                break;
            case R.id.g4:
                arr[3]=Turn;
                break;
            case R.id.g5:
                arr[4]=Turn;
                break;
            case R.id.g6:
                arr[5]=Turn;
                break;
            case R.id.g7:
                arr[6]=Turn;
                break;
            case R.id.g8:
                arr[7]=Turn;
                break;
            case R.id.g9:
                arr[8]=Turn;
                break;
        }
        if((arr[0]==1&&arr[1]==1&&arr[2]==1)||(arr[0]==1&&arr[3]==1&&arr[6]==1)||(arr[6]==1&&arr[7]==1&&arr[8]==1)||(arr[1]==1&&arr[4]==1&&arr[7]==1)||(arr[3]==1&&arr[4]==1&&arr[5]==1)||(arr[2]==1&&arr[5]==1&&arr[8]==1)||(arr[0]==1&&arr[4]==1&&arr[8]==1)||(arr[2]==1&&arr[4]==1&&arr[6]==1)){
            win=true;
            result.setText("CONGRATS!!"+getIntent().getExtras().getString("com.NameCollect.Player2")+" You WON!!");
           /* pl1=getIntent().getExtras().getString("com.NameCollect.Player1");
             pl2=getIntent().getExtras().getString("com.NameCollect.Player2");*/
            if(pl1.equalsIgnoreCase("YOU")||pl1.equalsIgnoreCase("COMPUTER")){
                pl1+="(1v1)";
            }
            if(pl2.equalsIgnoreCase("YOU")||pl2.equalsIgnoreCase("COMPUTER")){
                pl2+="(1v1)";
            }
            res=g.insertData(pl1,pl2,pl2+"WON");
            /*f(res==true)
                Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "Unsuccessful", Toast.LENGTH_SHORT).show();*/
        }
        if((arr[0]==0&&arr[1]==0&&arr[2]==0)||(arr[0]==0&&arr[3]==0&&arr[6]==0)||(arr[6]==0&&arr[7]==0&&arr[8]==0)||(arr[1]==0&&arr[4]==0&&arr[7]==0)||(arr[3]==0&&arr[4]==0&&arr[5]==0)||(arr[2]==0&&arr[5]==0&&arr[8]==0)||(arr[0]==0&&arr[4]==0&&arr[8]==0)||(arr[2]==0&&arr[4]==0&&arr[6]==0)){
            win=true;
            result.setText("CONGRATS!!"+getIntent().getExtras().getString("com.NameCollect.Player1")+" You WON!!");
            if(pl1.equalsIgnoreCase("YOU")||pl1.equalsIgnoreCase("COMPUTER")){
                pl1+="(1v1)";
            }
            else if(pl2.equalsIgnoreCase("YOU")||pl2.equalsIgnoreCase("COMPUTER")){
                pl2+="(1v1)";
            }
           res= g.insertData(pl1,pl2,pl1+"WON");
            /*if(res==true)
                Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "Unsuccessful", Toast.LENGTH_SHORT).show();*/
        }
        int k;
        for(k=0;k<9;k++){
            if(arr[k]==-1){
                k=-1;
                break;
            }
        }
        if(k!=-1&&win==false){
            result.setText("Match Draw!!!");
            if(pl1.equalsIgnoreCase("YOU")||pl1.equalsIgnoreCase("COMPUTER")){
                pl1+="(1v1)";
            }
            else if(pl2.equalsIgnoreCase("YOU")||pl2.equalsIgnoreCase("COMPUTER")){
                pl2+="(1v1)";
            }
            res=g.insertData(pl1,pl2,"DRAW");
           /* if(res==true)
                Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "Unsuccessful", Toast.LENGTH_SHORT).show();*/
        }
    }
    public void replay(View v){
        img=findViewById(R.id.g1);
        img.setImageResource(0);
        img=findViewById(R.id.g2);
        img.setImageResource(0);
        img=findViewById(R.id.g3);
        img.setImageResource(0);
        img=findViewById(R.id.g4);
        img.setImageResource(0);
        img=findViewById(R.id.g5);
        img.setImageResource(0);
        img=findViewById(R.id.g6);
        img.setImageResource(0);
        img=findViewById(R.id.g7);
        img.setImageResource(0);
        img=findViewById(R.id.g8);
        img.setImageResource(0);
        img=findViewById(R.id.g9);
        img.setImageResource(0);
        turn=findViewById(R.id.turn);
        result=findViewById(R.id.result);
        turn.setText(getIntent().getExtras().getString("com.NameCollect.Player1")+"'s Turn");
        for(int i=0;i<9;i++)
            arr[i]=-1;
        win=false;
        count=0;
        Turn=1;
        result.setText("");
    }
}