package com.example.simple_adaptordemo.custom_simpleadaptor;

import android.content.Context;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Custom_Adaptor extends SimpleAdapter {
    Context context;
    ArrayList<HashMap<String,?>> arrayList;
    public Custom_Adaptor(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
        this.context=context;
        this.arrayList=(ArrayList<HashMap<String,?>>)data;
    }
}
