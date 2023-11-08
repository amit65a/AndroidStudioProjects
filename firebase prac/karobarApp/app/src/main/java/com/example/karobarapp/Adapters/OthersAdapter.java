package com.example.karobarapp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.karobarapp.MBC.updatembc;
import com.example.karobarapp.Others.updateothers;
import com.example.karobarapp.R;
import com.example.karobarapp.models.MBCDataModel;
import com.example.karobarapp.models.OthersDataModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OthersAdapter extends RecyclerView.Adapter<OthersAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<OthersDataModel> dataModelList;


    public OthersAdapter(Context context) {
        this.context = context;
        dataModelList = new ArrayList<>();
    }

    public void addModel(OthersDataModel othersDataModel) {
        dataModelList.add(othersDataModel);
        notifyDataSetChanged();
    }

    public void clear(OthersDataModel othersDataModel) {
        dataModelList.clear();
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public OthersAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.datarowothers, parent, false);
        return new OthersAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OthersAdapter.MyViewHolder holder, int position) {
        holder.bindViews(dataModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataModelList.size();
    }


    public ArrayList<OthersDataModel> getAll() {
        return dataModelList;
    }

    public ArrayList<OthersDataModel> getSelected() {
        ArrayList<OthersDataModel> selected = new ArrayList<>();
        for (int i = 0; i < dataModelList.size(); i++) {
            if (dataModelList.get(i).isChecked()) {
                selected.add(dataModelList.get(i));
            }
        }
        return selected;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView mitiov, telov,karobarov, parinamov, darov, kaifiyatov;
        ImageView tickimgothers;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tickimgothers=itemView.findViewById(R.id.tickimgothers);
            mitiov = itemView.findViewById(R.id.mitiov);
            telov = itemView.findViewById(R.id.telov);
            karobarov = itemView.findViewById(R.id.karobarov);
            parinamov = itemView.findViewById(R.id.parinamov);
            darov = itemView.findViewById(R.id.darov);
            kaifiyatov = itemView.findViewById(R.id.kaifiyatov);
        }


        @SuppressLint("ResourceAsColor")
        public void bindViews(OthersDataModel othersDataModel) {
            mitiov.setText(othersDataModel.getMitio());
            telov.setText(othersDataModel.getTelo());
            karobarov.setText(othersDataModel.getKarobaro());
            parinamov.setText(othersDataModel.getParinamo());
            darov.setText(othersDataModel.getDaro());
            kaifiyatov.setText(othersDataModel.getKaifiyato());
            tickimgothers.setVisibility(othersDataModel.isChecked() ? View.VISIBLE : View.GONE);
            itemView.setBackgroundColor(othersDataModel.isChecked() ? (R.color.clickedColor) : (Color.TRANSPARENT));


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, updateothers.class);
                    intent.putExtra("modelothers", othersDataModel);
                    context.startActivity(intent);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    othersDataModel.setChecked(!othersDataModel.isChecked());
                    tickimgothers.setVisibility(othersDataModel.isChecked() ? View.VISIBLE : View.GONE);
                    itemView.setBackgroundColor(othersDataModel.isChecked() ? (R.color.clickedColor) : (Color.TRANSPARENT));

                    return true;
                }
            });
        }
    }
}
