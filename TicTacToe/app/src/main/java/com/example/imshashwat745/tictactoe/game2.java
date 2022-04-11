package com.example.imshashwat745.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class game2 extends AppCompatActivity {
    TextView gtextView;
    ImageView imgg;
    TextView gturn;
    TextView gresult;
    MediaPlayer gmp;
    int gTurn=1;
    int garr[]=new int[9];
    int gcount=0;
    boolean gwin=false;
    int random_int;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2);
        gtextView=findViewById(R.id.gturn);
        imgg=findViewById(R.id.gg1);
        imgg.setImageResource(0);
        imgg=findViewById(R.id.gg2);
        imgg.setImageResource(0);
        imgg=findViewById(R.id.gg3);
        imgg.setImageResource(0);
        imgg=findViewById(R.id.gg4);
        imgg.setImageResource(0);
        imgg=findViewById(R.id.gg5);
        imgg.setImageResource(0);
        imgg=findViewById(R.id.gg6);
        imgg.setImageResource(0);
        imgg=findViewById(R.id.gg7);
        imgg.setImageResource(0);
        imgg=findViewById(R.id.gg8);
        imgg.setImageResource(0);
        imgg=findViewById(R.id.gg9);
        imgg.setImageResource(0);
        gturn=findViewById(R.id.gturn);
        gresult=findViewById(R.id.gresult);
        gturn.setText("Your Turn!!");
        for(int i=0;i<9;i++)
            garr[i]=-1;
    }
    public void set(View v){
        gmp= MediaPlayer.create(this, R.raw.tap);
        imgg=findViewById(v.getId());
        if(imgg.getDrawable()==null&&gwin==false){
            if(gTurn==1){
                imgg.setImageResource(R.drawable.x);
                gturn.setText("");
                check();
                gTurn=0;
                gcount+=2;
                if(gwin==false)
                logic();
            }
        }
        else{
            if(gwin==false)
                Toast.makeText(this, "Can't set here,already there ", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "Game is over Tap replay button to play again", Toast.LENGTH_SHORT).show();
        }
    }
    public void check(){
        boolean res;
        database dbs=new database(this);
        switch(imgg.getId()){
            case R.id.gg1:
                garr[0]=gTurn;
                break;
            case R.id.gg2:
                garr[1]=gTurn;
                break;
            case R.id.gg3:
                garr[2]=gTurn;
                break;
            case R.id.gg4:
                garr[3]=gTurn;
                break;
            case R.id.gg5:
                garr[4]=gTurn;
                break;
            case R.id.gg6:
                garr[5]=gTurn;
                break;
            case R.id.gg7:
                garr[6]=gTurn;
                break;
            case R.id.gg8:
                garr[7]=gTurn;
                break;
            case R.id.gg9:
                garr[8]=gTurn;
                break;
        }
        if((garr[0]==1&&garr[1]==1&&garr[2]==1)||(garr[0]==1&&garr[3]==1&&garr[6]==1)||(garr[6]==1&&garr[7]==1&&garr[8]==1)||(garr[1]==1&&garr[4]==1&&garr[7]==1)||(garr[3]==1&&garr[4]==1&&garr[5]==1)||(garr[2]==1&&garr[5]==1&&garr[8]==1)||(garr[0]==1&&garr[4]==1&&garr[8]==1)||(garr[2]==1&&garr[4]==1&&garr[6]==1)){
            gwin=true;
            gresult.setText("CONGRATS YOU WON!!!");
            gturn.setText("GAME OVER!!");
            res= dbs.insertData("YOU","COMPUTER", "YOU WON");
        }
        if((garr[0]==0&&garr[1]==0&&garr[2]==0)||(garr[0]==0&&garr[3]==0&&garr[6]==0)||(garr[6]==0&&garr[7]==0&&garr[8]==0)||(garr[1]==0&&garr[4]==0&&garr[7]==0)||(garr[3]==0&&garr[4]==0&&garr[5]==0)||(garr[2]==0&&garr[5]==0&&garr[8]==0)||(garr[0]==0&&garr[4]==0&&garr[8]==0)||(garr[2]==0&&garr[4]==0&&garr[6]==0)){
            gwin=true;
            gresult.setText("BETTER LUCK NEXT TIME YOU LOST");
            gturn.setText("GAME OVER!!");
            res= dbs.insertData("YOU","COMPUTER", "COMPUTER WON");
        }
        int k;
        for(k=0;k<9;k++){
            if(garr[k]==-1){
                k=-1;
                break;
            }
        }
        if(k!=-1&&gwin==false){
            gwin=true;
            gresult.setText("Match Draw!!!");
            gturn.setText("GAME OVER!!");
            res= dbs.insertData("YOU","COMPUTER", "DRAW");
        }
    }
    /*/public void compute(){
        if(gcount==2){

            while(true){
                random_int= (int)Math.floor(Math.random()*(8-0+1)+0);
                if(garr[random_int]==-1)
                    break;
            }
            setImg(random_int);
            imgg.setImageResource(R.drawable.o);
            check();
            gTurn =1;
            gturn.setText("Your Turn!!");
         }
            else{
                logic();
            }

    }*/
    public int logic(){
        int j,k,c=0,d=0;
        //horizontal check win
        for(j=0;j<=6;j+=3){
            c=0;d=0;
            for(k=j;k<j+3;k++){
                if(garr[k]==0)
                    c++;
                if(garr[k]==-1)
                    d++;
            }
            if(c==2&&d==1){
                if(garr[j]==-1)
                    random_int=j;
                else if(garr[j+1]==-1)
                    random_int=j+1;
                else
                    random_int=j+2;
                setImg(random_int);
                imgg.setImageResource(R.drawable.o);
                check();
                gTurn=1;
                gturn.setText("Your Turn!!");
                return 0;
            }
        }
        //vertical check win
        for(j=0;j<=2;j++){
            c=0;d=0;
            for(k=j;k<j+7;k+=3){
                if(garr[k]==0)
                    c++;
                if(garr[k]==-1)
                    d++;
            }
            if(c==2&&d==1){
                if(garr[j]==-1)
                    random_int=j;
                else if(garr[j+3]==-1)
                    random_int=j+3;
                else
                    random_int=j+6;
                setImg(random_int);
                imgg.setImageResource(R.drawable.o);
                check();
                gTurn=1;
                gturn.setText("Your Turn!!");
                return 0;
            }
        }
        //diagonal_left check win
       c=0;d=0;
        for(j=0;j<9;j+=4){
            if(garr[j]==0)
                c++;
            if(garr[j]==-1)
                d++;
        }
        if(c==2&&d==1){
            if(garr[0]==-1)
                random_int=0;
            else if(garr[4]==-1)
                random_int=4;
            else
                random_int=8;
            setImg(random_int);
            imgg.setImageResource(R.drawable.o);
            check();
            gTurn=1;
            gturn.setText("Your Turn!!");
            return 0;
        }
        //diagonal_right check win
        c=0;d=0;
        for(j=2;j<7;j+=2){
            if(garr[j]==0)
                c++;
            if(garr[j]==-1)
                d++;
        }
        if(c==2&&d==1){
            if(garr[2]==-1)
                random_int=2;
            else if(garr[4]==-1)
                random_int=4;
            else
                random_int=6;
            setImg(random_int);
            imgg.setImageResource(R.drawable.o);
            check();
            gTurn=1;
            gturn.setText("Your Turn!!");
            return 0;
        }
        //.................................................................now checking for lose.............................................................................................
        //horizontal check lose
        for(j=0;j<=6;j+=3){
            c=0;d=0;
            for(k=j;k<j+3;k++){
                if(garr[k]==1)
                    c++;
                if(garr[k]==-1)
                    d++;
            }
            if(c==2&&d==1){
                if(garr[j]==-1)
                    random_int=j;
                else if(garr[j+1]==-1)
                    random_int=j+1;
                else
                    random_int=j+2;
                setImg(random_int);
                imgg.setImageResource(R.drawable.o);
                check();
                gTurn=1;
                gturn.setText("Your Turn!!");
                return 0;
            }
        }
        //vertical check lose
        for(j=0;j<=2;j++){
            c=0;d=0;
            for(k=j;k<j+7;k+=3){
                if(garr[k]==1)
                    c++;
                if(garr[k]==-1)
                    d++;
            }
            if(c==2&&d==1){
                if(garr[j]==-1)
                    random_int=j;
                else if(garr[j+3]==-1)
                    random_int=j+3;
                else
                    random_int=j+6;
                setImg(random_int);
                imgg.setImageResource(R.drawable.o);
                check();
                gTurn=1;
                gturn.setText("Your Turn!!");
                return 0;
            }
        }
        //diagonal_left check lose
        c=0;d=0;
        for(j=0;j<9;j+=4){
            if(garr[j]==1)
                c++;
            if(garr[j]==-1)
                d++;
        }
        if(c==2&&d==1){
            if(garr[0]==-1)
                random_int=0;
            else if(garr[4]==-1)
                random_int=4;
            else
                random_int=8;
            setImg(random_int);
            imgg.setImageResource(R.drawable.o);
            check();
            gTurn=1;
            gturn.setText("Your Turn!!");
            return 0;
        }
        //diagonal_right check lose
        c=0;d=0;
        for(j=2;j<7;j+=2){
            if(garr[j]==1)
                c++;
            if(garr[j]==-1)
                d++;
        }
        if(c==2&&d==1){
            if(garr[2]==-1)
                random_int=2;
            else if(garr[4]==-1)
                random_int=4;
            else
                random_int=6;
            setImg(random_int);
            imgg.setImageResource(R.drawable.o);
            check();
            gTurn=1;
            gturn.setText("Your Turn!!");
            return 0;
        }
        while(1<100){
            random_int= (int)(Math.random()*(8-0+1)+0);
            setImg(random_int);
            if(imgg.getDrawable()==null)
                break;
        }
        setImg(random_int);
        imgg.setImageResource(R.drawable.o);
        check();
        gTurn =1;
        gturn.setText("Your Turn!!");
        return 0;

    }
    public void replay(View view){
        imgg=findViewById(R.id.gg1);
        imgg.setImageResource(0);
        imgg=findViewById(R.id.gg2);
        imgg.setImageResource(0);
        imgg=findViewById(R.id.gg3);
        imgg.setImageResource(0);
        imgg=findViewById(R.id.gg4);
        imgg.setImageResource(0);
        imgg=findViewById(R.id.gg5);
        imgg.setImageResource(0);
        imgg=findViewById(R.id.gg6);
        imgg.setImageResource(0);
        imgg=findViewById(R.id.gg7);
        imgg.setImageResource(0);
        imgg=findViewById(R.id.gg8);
        imgg.setImageResource(0);
        imgg=findViewById(R.id.gg9);
        imgg.setImageResource(0);
        gturn=findViewById(R.id.gturn);
        gresult=findViewById(R.id.gresult);
        gturn.setText("Your Turn!!");
        for(int i=0;i<9;i++)
            garr[i]=-1;
        gwin=false;
        gcount=0;
        gTurn=1;
        gresult.setText("");
    }
    public void setImg(int vall){
        switch(vall){
            case 0:
                imgg=findViewById(R.id.gg1);
                break;
            case 1:
                imgg=findViewById(R.id.gg2);
                break;
            case 2:
                imgg=findViewById(R.id.gg3);
                break;
            case 3:
                imgg=findViewById(R.id.gg4);
                break;
            case 4:
                imgg=findViewById(R.id.gg5);
                break;
            case 5:
                imgg=findViewById(R.id.gg6);
                break;
            case 6:
                imgg=findViewById(R.id.gg7);
                break;
            case 7:
                imgg=findViewById(R.id.gg8);
                break;
            case 8:
                imgg=findViewById(R.id.gg9);
                break;
        }
    }
}