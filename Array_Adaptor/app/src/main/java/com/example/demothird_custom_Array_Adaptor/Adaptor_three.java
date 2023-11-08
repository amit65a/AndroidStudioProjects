package com.example.demothird_custom_Array_Adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.array_adaptor.R;

import java.util.ArrayList;

public class Adaptor_three extends ArrayAdapter<animallist> {

    ArrayList<animallist> animal_list;
    LayoutInflater layoutInflater;
    public Adaptor_three(@NonNull Context context, int resource, ArrayList<animallist> objects) {
        super(context, resource, objects);
        animal_list=objects;
        layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return animal_list.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v=layoutInflater.inflate(R.layout.uiview_three,null);
        ImageView imageView=(ImageView) v.findViewById(R.id.imgeview_three);
        TextView textView=(TextView) v.findViewById(R.id.tv_three);
        imageView.setImageResource(animal_list.get(position).getAnimal_img());
        textView.setText(animal_list.get(position).getAnimal_name());
        return v;
    }
}
