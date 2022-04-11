package com.example.imshashwat745.tictactoe;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
public class choose extends AppCompatActivity {
    String pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
    }
    public void vscomputer(View view){
        Intent vsc=new Intent(this,game2.class);
        startActivity(vsc);
    }
    public void vsplayer(View view){
        Intent vsp=new Intent(this,NameCollect.class);
        startActivity(vsp);
    }
    public void matchHistory(View view){


            Intent mh2 = new Intent(this, MatchHistory.class);
            startActivity(mh2);

    }
}