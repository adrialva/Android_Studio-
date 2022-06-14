package com.example.auth4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Opciones extends AppCompatActivity {

    private Button mButtonSonido, mButtonAyuda;

    public FirebaseAuth users;
    DatabaseReference mDatabase;
    private FirebaseDatabase data = FirebaseDatabase.getInstance();

    Login user = new Login();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones);
        users=FirebaseAuth.getInstance();
        mButtonAyuda = (Button) findViewById(R.id.btn_Botones);
        mButtonSonido = (Button) findViewById(R.id.btn_Sonido);

        mButtonSonido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Opciones.this, Sonido.class));
                finish();
            }
        });
        mButtonAyuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Opciones.this, Botones.class));
                finish();
            }
        });
    }
}