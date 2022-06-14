package com.example.auth3;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Sonido extends Fragment {

    private Button mbotonSignOut, mbotonGuardar;
    private FirebaseAuth mAuth;
    private FirebaseDatabase data = FirebaseDatabase.getInstance();
    DatabaseReference mDatabase;
    DatabaseReference ref1 = data.getReference("Mensajes");

    private static final int rec_spch_inpt=100;
    private TextView mEntVoz;
    private ImageButton mBotonHablar;

    public Sonido() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sonido, container, false);

        mEntVoz = view.findViewById(R.id.txtEntrada);
        mBotonHablar = view.findViewById(R.id.btnHablar);
        return view;
    }
}