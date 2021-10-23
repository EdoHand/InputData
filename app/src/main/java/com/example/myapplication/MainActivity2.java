package com.example.myapplication;


import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity2 extends MainActivity {
    EditText inputNama, inputUmur, inputAlamat, inputNim;
    TextView hasilNama, hasilUmur, hasilAlamat, hasilNim;
    Button btn_regist;

    UseHelpers useHelpers;
    DatabaseReference reference;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_main);
        fab = findViewById(R.id.float_btn);
        inputNim = findViewById(R.id.inputNim);
        hasilNim = findViewById(R.id.hasilNim);
        inputNama = findViewById(R.id.inputNama);
        hasilNama = findViewById(R.id.hasilNama);
        inputUmur = findViewById(R.id.inputUmur);
        hasilUmur = findViewById(R.id.hasilUmur);
        inputAlamat = findViewById(R.id.inputAlamat);
        hasilAlamat = findViewById(R.id.hasilAlamat);
        btn_regist = findViewById(R.id.btn_regist);
        useHelpers = new UseHelpers();
        reference = FirebaseDatabase.getInstance().getReference().child("Mahasiswa");
        btn_regist.setOnClickListener(e -> {
            int nim = Integer.parseInt(inputNim.getText().toString().trim());
            int age = Integer.parseInt(inputUmur.getText().toString().trim());
            useHelpers.setNama(inputNama.getText().toString().trim());
            useHelpers.setAlamat(inputAlamat.getText().toString().trim());
            useHelpers.setNim(nim);
            useHelpers.setUmur(age);
            reference.child(String.valueOf(nim)).setValue(useHelpers);
            Toast.makeText(MainActivity2.this, "Succes", Toast.LENGTH_SHORT).show();
//            nim = inputNim.getText().toString();
//            hasilNim.setText(nim);
//            nama = inputNama.getText().toString();
//            hasilNama.setText(nama);
//            umur = inputUmur.getText().toString();
//            hasilUmur.setText(umur);
//            alamat = inputAlamat.getText().toString();
//            hasilAlamat.setText(alamat);
        });
    }
}


