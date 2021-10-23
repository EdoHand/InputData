package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

public class app extends MainActivity implements View.OnClickListener{

    private CardView cardview1, cardview2, cardview3;
    Button logout;
    FirebaseAuth Auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app);

        cardview1 = findViewById(R.id.cardview1);
        cardview2 = findViewById(R.id.cardview2);
        cardview3 = findViewById(R.id.cardview3);
        logout = findViewById(R.id.logoutbt);

        cardview1.setOnClickListener(this);
        cardview2.setOnClickListener(this);
        cardview3.setOnClickListener(this);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }

    @Override
    public void onClick(View view) {
        Intent i ;

        switch (view.getId()){
            case R.id.cardview1:
                i = new Intent(this,MainActivity2.class);
                startActivity(i);
                break;
            case R.id.cardview2:
                i = new Intent(this, showData.class);
                startActivity(i);
                break;
            case R.id.cardview3:
                i = new Intent(this, info.class);
                startActivity(i);
                break;
        }
    }
}