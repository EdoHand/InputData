package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends app {
    EditText et1,et2,et3,et4;
    TextView tv1,tv2;
    Button register;
    ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et1 = findViewById(R.id.mName);
        et2 = findViewById(R.id.mEmail);
        et3 = findViewById(R.id.mPass);
        et4 = findViewById(R.id.mPhone);
        tv1 = findViewById(R.id.Authentifikasi);
        tv2 = findViewById(R.id.tvAccount);
        register = findViewById(R.id.btRegist);

        mAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);

        if (mAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),app.class));
            finish();
        }

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = et2.getText().toString().trim();
                String pass = et3.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    et2.setError("Email is Required!");
                    return;
                }

                if (TextUtils.isEmpty(pass)){
                    et3.setError("Password is Required!");
                    return;
                }
                if (pass.length() < 6 ){
                    et3.setError("Password Must be > 6 Characters");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                       if (task.isSuccessful()){
                           Toast.makeText(Register.this, "User created.", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), app.class));
                    }else{
                           Toast.makeText(Register.this, "Error!" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                           progressBar.setVisibility(View.GONE);
                       }
                }
            });
        }
    });
}
}