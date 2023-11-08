package com.example.adaptors_android.demoone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.adaptors_android.R;

public class MyAdaptorOne extends BaseAdapter {

    Context context;
    int[] data;
    LayoutInflater inflater;
    MyAdaptorOne(Context context,int[]data){
        this.context=context;
        this.data=data;
        this.inflater=LayoutInflater.from(context);
    }
    @Override
    //getCount counts the number of data items in Datasource
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //to get view we use inflater

        view=inflater.inflate(R.layout.ui_componentone,null);
        ImageView imageview_one=(ImageView) view.findViewById(R.id.imageview_one);
        imageview_one.setImageResource(data[i]);
        return view;
    }
}
