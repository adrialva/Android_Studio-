package com.example.auth4;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Sonido extends AppCompatActivity {

    private Button mbotonSignOut, mbotonGuardar;
    private FirebaseAuth mAuth;
    private FirebaseDatabase data = FirebaseDatabase.getInstance();
    DatabaseReference mDatabase;
    DatabaseReference ref1 = data.getReference("Mensajes");

    private static final int rec_spch_inpt=100;
    private TextView mEntVoz;
    private ImageButton mBotonHablar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sonido);

        mbotonSignOut = (Button) findViewById(R.id.btnSignOut);
        mbotonGuardar = (Button) findViewById(R.id.btnGuardar);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();


        mEntVoz = findViewById(R.id.txtEntrada);
        mBotonHablar = (ImageButton) findViewById(R.id.btnHablar);

        mbotonSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                startActivity(new Intent(Sonido.this,MainActivity.class ));
                finish();
            }
        });

        mBotonHablar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciarGrabacion();
            }
        });

        mbotonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GuardarGrabacion();
                enviarMensaje();
            }
        });
    }

    private void iniciarGrabacion(){
        Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        i.putExtra(RecognizerIntent.EXTRA_PROMPT, "Dime el mensaje que quieres enviar");
        try{
            startActivityForResult(i, rec_spch_inpt);
        } catch(ActivityNotFoundException e){

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode){
            case rec_spch_inpt: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    mEntVoz.setText(result.get(0));
                }
                break;
            }
        }

    }
    private void GuardarGrabacion(){

        Map<String, Object> map = new HashMap<>();
        map.put("Mensaje",mEntVoz.getText());

        String id = mAuth.getCurrentUser().getUid();
        ref1.child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {

            @Override
            public void onComplete(@NonNull Task<Void> task3) {
                if(task3.isSuccessful()) {

                    startActivity(new Intent(Sonido.this, Sonido.class));
                    finish();
                }else {
                    Toast.makeText(Sonido.this, "No se envio el mensaje correctamente", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void enviarMensaje(){
        /*if (cod.equals){
            guardar;
            eviar;
        }else{
            o se puede eviar mesaje
        }*/
        MainActivity ma = new MainActivity();

        String id = ma.users.getCurrentUser().getUid();
        //DatabaseReference send = data.getReference("Codigo").child(ma.users.getUid()).child(user2.getId);
    }
}