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

import com.example.karobarapp.Kharid.updatekharid;
import com.example.karobarapp.R;
import com.example.karobarapp.models.KharidDataModel;
import com.example.karobarapp.models.OthersDataModel;

import java.util.ArrayList;
import java.util.List;

public class KharidAdapter extends RecyclerView.Adapter<KharidAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<KharidDataModel> dataModelList;


    public KharidAdapter(Context context) {
        this.context = context;
        dataModelList = new ArrayList<>();
    }

    public void addModel(KharidDataModel kharidDataModel) {
        dataModelList.add(kharidDataModel);
        notifyDataSetChanged();
    }

    public void clear(KharidDataModel kharidDataModel) {
        dataModelList.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public KharidAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.datarowkharid, parent, false);
        return new KharidAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KharidAdapter.MyViewHolder holder, int position) {
        holder.bindViews(dataModelList.get(position));

    }

    @Override
    public int getItemCount() {
        return dataModelList.size();
    }

    public ArrayList<KharidDataModel> getSelected() {
        ArrayList<KharidDataModel> selected = new ArrayList<>();
        for (int i = 0; i < dataModelList.size(); i++) {
            if (dataModelList.get(i).isChecked()) {
                selected.add(dataModelList.get(i));
            }
        }
        return selected;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView mitikv, telkv, batakv, parinamkv, darkv, kaifiyatkv;
        ImageView tickimgkharid;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tickimgkharid=itemView.findViewById(R.id.tickimgkharid);
            mitikv=itemView.findViewById(R.id.mitikv);
            telkv=itemView.findViewById(R.id.telkv);
            batakv=itemView.findViewById(R.id.batakv);
            parinamkv=itemView.findViewById(R.id.parinamkv);
            darkv=itemView.findViewById(R.id.darkv);
            kaifiyatkv=itemView.findViewById(R.id.kaifiyatkv);
        }

        @SuppressLint("ResourceAsColor")
        public void bindViews(KharidDataModel kharidDataModel) {
            mitikv.setText(kharidDataModel.getMitik());
            telkv.setText(kharidDataModel.getTelk());
            batakv.setText(kharidDataModel.getBatak());
            parinamkv.setText(kharidDataModel.getParinamk());
            darkv.setText(kharidDataModel.getDark());
            kaifiyatkv.setText(kharidDataModel.getKaifiyatk());
            tickimgkharid.setVisibility(kharidDataModel.isChecked() ? View.VISIBLE : View.GONE);
            itemView.setBackgroundColor(kharidDataModel.isChecked() ? (R.color.clickedColor) : (Color.TRANSPARENT));


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, updatekharid.class);
                    intent.putExtra("modelkharid",kharidDataModel);
                    context.startActivity(intent);
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    kharidDataModel.setChecked(!kharidDataModel.isChecked());
                    tickimgkharid.setVisibility(kharidDataModel.isChecked() ? View.VISIBLE : View.GONE);
                    itemView.setBackgroundColor(kharidDataModel.isChecked() ? (R.color.clickedColor) : (Color.TRANSPARENT));
                    return true;
                }
            });
        }
    }
}
