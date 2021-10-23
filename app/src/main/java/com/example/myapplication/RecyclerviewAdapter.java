package com.example.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.UseHelpers;

import java.util.ArrayList;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.MyViewHolder>{
    private Context context;
    final ArrayList<UseHelpers> dataMhs;

    public RecyclerviewAdapter(Context cont, ArrayList<UseHelpers>data){
        context = cont;
        dataMhs = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mhs, parent,false); //convert layout
        return new MyViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.nama.setText(String.valueOf(dataMhs.get(position).getNama()));
        holder.nim.setText(String.valueOf(dataMhs.get(position).getNim()));
        holder.alamat.setText(String.valueOf(dataMhs.get(position).getAlamat()));
        holder.umur.setText(String.valueOf(dataMhs.get(position).getUmur()));

    }


    @Override
    public int getItemCount() {
        return dataMhs.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nama, nim, alamat, umur;

        public MyViewHolder(@NonNull View itemView, Context ctx) {
            super(itemView);
            context = ctx;
            nama = itemView.findViewById(R.id.hasilNama2);
            nim = itemView.findViewById(R.id.hasilNim2);
            alamat = itemView.findViewById(R.id.hasilAlamat2);
            umur = itemView.findViewById(R.id.hasilUmur2);

        }
    }
}
