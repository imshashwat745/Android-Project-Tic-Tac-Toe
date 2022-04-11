package com.example.imshashwat745.tictactoe;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import  android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class database extends SQLiteOpenHelper {
    private static final String dbname="MyData.db";
    private static final String table1="Passwordss.db";
    private static final String table2="MatchHistory.db";
    public database(@Nullable Context context) {
        super(context, dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String q="create table history (_id integer primary key autoincrement,name1 text,name2 text,result text)";
       // String p="create table password (_id integer primary key autoincrement,pwrd text)";
        sqLiteDatabase.execSQL(q);
        //sqLiteDatabase.execSQL(p);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
    sqLiteDatabase.execSQL("drop table if exists history");
    //sqLiteDatabase.execSQL("drop table if exists password");
    onCreate(sqLiteDatabase);
    }

    public boolean insertData(String name1, String name2, String result){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cr=getinfo();
        ContentValues c=new ContentValues();
        String countQuery="SELECT * FROM history";
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        int id;
        if(cr.getCount()==10){
        //int min=Integer.MAX_VALUE;
        Log.d("Before min",String.valueOf(count));
                    cr.moveToFirst();
        Log.d("reach miniiiii","yes");
                    id = Integer.parseInt(cr.getString(0));
                Log.d("reach min",String.valueOf(id));

                boolean res=deleteDatabyID(id);}
            cr.close();

        c.put("name1",name1);
        c.put("name2",name2);
        c.put("result",result);
        long r=db.insert("history",null,c);
        if(r==-1) return false;
        return true;
    }
    public Cursor getinfo(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from history",null);
        return cursor;
    }
    public boolean deleteDatabyID(int idd){
        SQLiteDatabase db=this.getWritableDatabase();
        //db.execSQL("delete from history where _id='"+Integer.toString(idd)+"'");
        Cursor cursor=db.rawQuery("select * from history where _id=?",new String[]{String.valueOf(idd)});
        if(cursor.getCount()>0){
            long r=db.delete("history","_id=?",new String[]{String.valueOf(idd)});
            if(r==-1)return false;
            return true;
        }
        return false;
    }
    public Cursor getData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from history",null);
        return cursor;
    }
    //2nd



/*
    public Cursor getpassData(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from password",null);
        return cursor;
    }
    public boolean insertPass(String passwordd){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues c=new ContentValues();
        String Query="SELECT * FROM password";
        //Cursor cursor = db.rawQuery(Query, null);
        c.put("pwrd",passwordd);
        long r=db.insert("password",null,c);
        if(r==-1)
            return false;
        return true;
    }
    public boolean deletePass(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=getpassData();
        String p=cursor.getString(0);
        //db.execSQL("delete from history where _id='"+Integer.toString(idd)+"'");
        cursor=db.rawQuery("select * from password where pwrd=?",new String[]{p});
        if(cursor.getCount()>0){
            long r=db.delete("history","_id=?",new String[]{p});
            if(r==-1)return false;
            return true;
        }
        return false;
    }*/

}
