package com.example.pojofirebase.model;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pojofirebase.DetailsActivity;
import com.example.pojofirebase.R;
import com.example.pojofirebase.network.utils;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.List;

public class userAdapter extends RecyclerView.Adapter<userAdapter.MyViewHolder> {
    public static final String USER_KEY = "user_key";
    private Context mcontext;
    private List<User> mDatalist;

    public userAdapter(Context mcontext, List<User> mDatalist) {
        this.mcontext = mcontext;
        this.mDatalist = mDatalist;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView= LayoutInflater.from(mcontext).inflate(R.layout.list_item,parent,false);
        return new   MyViewHolder(rootView);
    }

    @Override
    public int getItemCount() {
        return mDatalist.size();
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        User user=mDatalist.get(position);
        holder.listView.setText(user.getName()+" | "+user.getAge());
        holder.listView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uid= user.getUid();
                Intent intent=new Intent(mcontext, DetailsActivity.class);
                intent.putExtra(USER_KEY,uid);
                mcontext.startActivity(intent);
            }
        });

        holder.listView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                String userID= user.getUid();
                Task<Void> voidtask = utils.removeuser(userID);
                voidtask.addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(mcontext, "user removed", Toast.LENGTH_SHORT).show();
                    }
                });



                return true;
            }
        });

    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView listView;
        public MyViewHolder(View itemView){
            super(itemView);
            listView=itemView.findViewById(R.id.listView);
        }
    }
}
