package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.Model.UseHelpers;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class showData extends MainActivity
{
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView listMahasiswaView;
    private RecyclerviewAdapter recyclerviewAdapter;
    DatabaseReference mhsReference;
    ArrayList<UseHelpers> listMhs = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_data);
        listMahasiswaView = findViewById(R.id.list_mahasiswa);
        listMahasiswaView.setHasFixedSize(true);
        listMahasiswaView.setLayoutManager(new LinearLayoutManager(this));
        MyRecyclerView();

        mhsReference = FirebaseDatabase.getInstance().getReference().child("Mahasiswa");
        mhsReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    UseHelpers single = dataSnapshot.getValue(UseHelpers.class);
                    listMhs.add(single);
                }

                recyclerviewAdapter = new RecyclerviewAdapter(showData.this, listMhs);
                listMahasiswaView.setAdapter(recyclerviewAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Data Gagal Dimuat", Toast.LENGTH_LONG).show();
                Log.e("MyListActivity", error.getDetails() + " " + error.getMessage());
            }
        });
    }

    private void MyRecyclerView() {
        //Menggunakan Layout Manager, Dan Membuat List Secara Vertical
        layoutManager = new LinearLayoutManager(this);
        listMahasiswaView.setLayoutManager(layoutManager);
        listMahasiswaView.setHasFixedSize(true);

        //Membuat Underline pada Setiap Item Didalam List
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.line));
        listMahasiswaView.addItemDecoration(itemDecoration);
    }

    public void back(View view) {
        Intent i = new Intent(showData.this, app.class);
        startActivity(i);
    }
}

