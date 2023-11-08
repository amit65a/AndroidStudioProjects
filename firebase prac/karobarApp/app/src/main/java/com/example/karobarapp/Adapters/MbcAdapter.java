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
import com.example.karobarapp.R;
import com.example.karobarapp.models.MBCDataModel;

import java.util.ArrayList;
import java.util.List;

public class MbcAdapter extends RecyclerView.Adapter<MbcAdapter.MyViewHolder> {
    private Context context;
    private List<MBCDataModel> dataModelList;
    public MbcAdapter(Context context, List<MBCDataModel> mbcDataModels) {
        this.context = context;
        dataModelList = new ArrayList<>();
    }


    public void addModel(MBCDataModel mbcDataModel) {
        dataModelList.add(mbcDataModel);
        notifyDataSetChanged();
    }

    public void setDataModelList(ArrayList<MBCDataModel> dataModelList) {
        this.dataModelList = new ArrayList<>();
        this.dataModelList = dataModelList;
        notifyDataSetChanged();
    }

    public void clear(MBCDataModel mbcDataModel) {
        dataModelList.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.datarowmbc, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bindViews(dataModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataModelList.size();
    }

    public List<MBCDataModel> getAll() {
        return dataModelList;
    }

    public ArrayList<MBCDataModel> getSelected() {
        ArrayList<MBCDataModel> selected = new ArrayList<>();
        for (int i = 0; i < dataModelList.size(); i++) {
            if (dataModelList.get(i).isChecked()) {
                selected.add(dataModelList.get(i));
            }
        }
        return selected;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView mitimbcv, gadinombcv, telmbcv, parinammbcv, darmbcv, kaifiyatmbcv;
        private ImageView tickimgmbc;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tickimgmbc = itemView.findViewById(R.id.tickimgmbc);
            mitimbcv = itemView.findViewById(R.id.mitimbcv);
            gadinombcv = itemView.findViewById(R.id.gadinombcv);
            telmbcv = itemView.findViewById(R.id.telmbcv);
            parinammbcv = itemView.findViewById(R.id.parinammbcv);
            darmbcv = itemView.findViewById(R.id.darmbcv);
            kaifiyatmbcv = itemView.findViewById(R.id.kaifiyatmbcv);
        }


        @SuppressLint("ResourceAsColor")
        public void bindViews(MBCDataModel mbcDataModel) {
            mitimbcv.setText(mbcDataModel.getMiti());
            gadinombcv.setText(mbcDataModel.getGadino());
            telmbcv.setText(mbcDataModel.getTel());
            parinammbcv.setText(mbcDataModel.getParinam());
            darmbcv.setText(mbcDataModel.getDar());
            kaifiyatmbcv.setText(mbcDataModel.getKaifiyat());
            tickimgmbc.setVisibility(mbcDataModel.isChecked() ? View.VISIBLE : View.GONE);
            itemView.setBackgroundColor(mbcDataModel.isChecked() ? (R.color.clickedColor) : (Color.TRANSPARENT));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, updatembc.class);
                    intent.putExtra("model", mbcDataModel);
                    context.startActivity(intent);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mbcDataModel.setChecked(!mbcDataModel.isChecked());
                    tickimgmbc.setVisibility(mbcDataModel.isChecked() ? View.VISIBLE : View.GONE);
                    itemView.setBackgroundColor(mbcDataModel.isChecked() ? (R.color.clickedColor) : (Color.TRANSPARENT));

                    return true;
                }
            });
        }
    }
}

