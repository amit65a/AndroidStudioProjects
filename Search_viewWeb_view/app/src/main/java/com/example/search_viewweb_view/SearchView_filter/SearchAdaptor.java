package com.example.search_viewweb_view.SearchView_filter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.search_viewweb_view.R;

import java.util.ArrayList;

public class SearchAdaptor extends BaseAdapter {
    Context context;
    LayoutInflater layoutInflater;
    ArrayList al_names;
    ArrayList al_new_names;

    public SearchAdaptor(Context context, ArrayList names){
        this.context=context;
        this.al_names=names;
        layoutInflater=LayoutInflater.from(context);
        al_new_names =new ArrayList();
        al_new_names.addAll(al_names);
    }

    @Override
    public int getCount() {
        return al_names.size();
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
        View view1=layoutInflater.inflate(R.layout.ui_view_search,null);
        TextView tv=view1.findViewById(R.id.name);
        tv.setText(al_names.get(i).toString());
        return view1;
    }
    public void myfilter(String s){
        al_names.clear();
        for(Object o: al_new_names){
            if (o.toString().contains(s)){
                al_names.add(o.toString());
            }
        }
        notifyDataSetChanged();
    }
}
