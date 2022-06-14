package com.example.auth3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText mET_nombre, mET_mail, mET_password;
    private Button mButtonRegistrar, mButtonIrLogin;

    //Datos a registrar
    private String nombre ="";
    private String email ="";
    private String password ="";

    public FirebaseAuth users;
    DatabaseReference mDatabase;
    private FirebaseDatabase data = FirebaseDatabase.getInstance();

    public FirebaseAuth getUsers() {
        return users;
    }

    public void setUsers(FirebaseAuth users) {
        this.users = users;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        users = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mET_nombre = (EditText) findViewById(R.id.edit_TextName);
        mET_mail = (EditText) findViewById(R.id.edit_TextEmail);
        mET_password = (EditText) findViewById(R.id.edit_TextPass);
        mButtonRegistrar = (Button) findViewById(R.id.btn_register);
        mButtonIrLogin = (Button) findViewById(R.id.btn_SndLogin);

        mButtonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombre = mET_nombre.getText().toString();
                email = mET_mail.getText().toString();
                password = mET_password.getText().toString();

                if (!nombre.isEmpty() & !email.isEmpty() & !password.isEmpty()) {
                    if (password.length() >= 6) {
                        registrarUsuario();
                    } else {
                        Toast.makeText(MainActivity.this, "Ingrese al menos 6 caracteres", Toast.LENGTH_SHORT).show();
                    }
                    registrarUsuario();
                } else {
                    Toast.makeText(MainActivity.this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mButtonIrLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Login.class));
                //finish(); puede volver a la pagina anterior
            }
        });

    }

    private void registrarUsuario() {
        users.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {

                    Map<String, Object> map = new HashMap<>();
                    map.put("Nombre", nombre);
                    map.put("Email", email);
                    map.put("Password", password);

                    String id = users.getCurrentUser().getUid();
                    mDatabase.child("Usuarios").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            if(task2.isSuccessful()) {
                                startActivity(new Intent(MainActivity.this, Sonido.class));
                                finish();
                            }else {
                                Toast.makeText(MainActivity.this, "No se logro crear los datos correctamente ", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else {
                    Toast.makeText(MainActivity.this, "No se logro registrar este usuario ", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(users.getCurrentUser() != null){
            startActivity(new Intent(MainActivity.this, Sonido.class));
            finish();
        }
    }
}