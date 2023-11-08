package com.example.array_adaptor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivityone extends AppCompatActivity {

    ListView listview_one;
    String []name={"Deepak","Madhav","Madan","Gopal"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activityone);

        listview_one=findViewById(R.id.listview_one);

        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,R.layout.ui_viewone,R.id.textview_one,name);
        listview_one.setAdapter(arrayAdapter);
    }
}