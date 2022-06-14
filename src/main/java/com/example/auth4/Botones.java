package com.example.auth4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;
import java.util.Map;

import java.util.HashMap;

public class Botones extends AppCompatActivity {



    private Button mUno, mCuatro, mSiete, mEmergencia, mSesion;
    private FirebaseAuth mAuth;
    private FirebaseDatabase data = FirebaseDatabase.getInstance();
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference mDatabase;
    DatabaseReference ref1 = data.getReference("Emergencia");

    MediaPlayer mp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_botones);

        mUno = (Button) findViewById(R.id.btn_uno);
        mCuatro = (Button) findViewById(R.id.btn_cuatro);
        mSiete = (Button) findViewById(R.id.btn_siete);
        mEmergencia = (Button) findViewById(R.id.btn_diez);
        mSesion = (Button) findViewById(R.id.btn_cerrar_sesion);

        mp = MediaPlayer.create(this, R.raw.audio);
        mAuth = FirebaseAuth.getInstance();

        mUno.setText("1,2,3");
        mUno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vibrator vibrator = (Vibrator)getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(1000);
                EmergenciaUno();
            }
        });

        mCuatro.setText("4,5,6");
        mCuatro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vibrator vibrator = (Vibrator)getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(1000);
                EmergenciaCuatro();
            }
        });
        mSiete.setText("7,8,9");
        mSiete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vibrator vibrator = (Vibrator)getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(1000);
                EmergenciaSiete();
            }
        });
        mEmergencia.setText("10");
        mEmergencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vibrator vibrator = (Vibrator)getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(1000);
                mp.start();
                EmergenciaDiez();
            }
        });

        mSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                startActivity(new Intent(Botones.this,MainActivity.class ));
                finish();
            }
        });
    }

    //private void cox(){

      //  Login user = new Login();
        //if(user.email.equals() user.
    //}

    private void EmergenciaUno(){
        Map<String, Object> map = new HashMap<>();
        map.put("Emergencia", mUno.getText());

        String id = mAuth.getCurrentUser().getUid();
        ref1.child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(Botones.this, Botones.class));
                    finish();
                }else{
                    Toast.makeText(Botones.this, "No se envio el mensaje correctamente", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void EmergenciaCuatro(){
        Map<String, Object> map = new HashMap<>();
        map.put("Emergencia", mCuatro.getText());

        String id = mAuth.getCurrentUser().getUid();
        ref1.child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(Botones.this, Botones.class));
                    finish();
                }else{
                    Toast.makeText(Botones.this, "No se envio el mensaje correctamente", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void EmergenciaSiete(){
        Map<String, Object> map = new HashMap<>();
        map.put("Emergencia", mSiete.getText());

        String id = mAuth.getCurrentUser().getUid();
        ref1.child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(Botones.this, Botones.class));
                    finish();
                }else{
                    Toast.makeText(Botones.this, "No se envio el mensaje correctamente", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void EmergenciaDiez(){
        Map<String, Object> map = new HashMap<>();
        map.put("Emergencia", mEmergencia.getText());

        String id = mAuth.getCurrentUser().getUid();
        ref1.child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(Botones.this, Botones.class));
                    finish();
                }else{
                    Toast.makeText(Botones.this, "No se envio el mensaje correctamente", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}