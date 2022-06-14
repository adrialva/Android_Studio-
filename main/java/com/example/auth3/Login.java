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

public class Login extends AppCompatActivity {

    private EditText mET_mail, mET_password;
    private Button mButtonLogin;

    String email = "";
    String password ="";

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        mET_mail = (EditText) findViewById(R.id.edit_TextEmail);
        mET_password = (EditText) findViewById(R.id.edit_TextPass);
        mButtonLogin = (Button) findViewById(R.id.btn_login);

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = mET_mail.getText().toString();
                password = mET_password.getText().toString();

                if (!email.isEmpty() && !password.isEmpty()){
                    ingresoUsuario();
                }else{
                    Toast.makeText(Login.this, "Complete los espacios", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void ingresoUsuario(){
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(Login.this, Sonido.class));
                    finish();
                }else{
                    Toast.makeText(Login.this, "No se pudo iniciar sesion, compruebe los datos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}