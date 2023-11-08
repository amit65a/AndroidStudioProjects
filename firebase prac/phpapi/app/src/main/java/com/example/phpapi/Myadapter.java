package com.example.phpapi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Myadapter extends RecyclerView.Adapter<Myadapter.MyViewHolder> {
    ArrayList allName;
    ArrayList allEmail;
    Myadapter(ArrayList allName,ArrayList allEmail){
        this.allName=allName;
        this.allEmail=allEmail;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.single_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(allName.get(position).toString());
        holder.email.setText(allEmail.get(position).toString());

    }

    @Override
    public int getItemCount() {
        return allName.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView name,email;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.iname);
            email=itemView.findViewById(R.id.iemail);
        }
    }
}
