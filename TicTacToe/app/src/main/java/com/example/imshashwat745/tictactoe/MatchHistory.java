package com.example.imshashwat745.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MatchHistory extends AppCompatActivity {
    ListView listView;
    int i;
    database obj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_history);
        setdata();
    }
    public void setdata(){
        obj=new database(this);
        String arr[]=new String[10];
        Cursor crs=obj.getData();
        if(crs.getCount()==0){
            //arr[0]="No DATA to show,Play some matches ";
            for(i=0;i<10;i++){
               arr[i]="";
            }}
        else{
            i=-1;
            while(crs.moveToNext()){
                i++;
                arr[i]=crs.getString(1)+" vs\t "+crs.getString(2)+" -\t "+crs.getString(3);
            }
            for(i=i+1;i<10;i++){
                arr[i]="";
            }
        }
        listView=findViewById(R.id.listview1);
        ArrayAdapter ad=new ArrayAdapter(this, android.R.layout.simple_list_item_1,arr);
        listView.setAdapter(ad);
    }
    public void deleteIt(View view){
        obj=new database(this);
        Cursor crsr=obj.getData();int x;
        if(crsr.getCount()==0){
            Toast.makeText(this, "Nothing to delete!!", Toast.LENGTH_SHORT).show();
        }
        else{
            while(crsr.moveToNext()){
                x=Integer.parseInt(crsr.getString(0));
                obj.deleteDatabyID(x);
            }
            setdata();
        }
    }
    public void playGame(View view){
        Intent pg=new Intent(this,choose.class);
        startActivity(pg);
    }
    /*public void passto(View view){
        Intent gtp=new Intent(this,PasswordManagement.class);
        startActivity(gtp);
    }*/
   /* public void passManage(View view){
        Intent gtp=new Intent(this,PasswordManagement.class);
        startActivity(gtp);*/
   // }
}
