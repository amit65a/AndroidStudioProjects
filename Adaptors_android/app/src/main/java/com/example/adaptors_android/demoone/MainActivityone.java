package com.example.adaptors_android.demoone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import com.example.adaptors_android.R;


public class MainActivityone extends AppCompatActivity {
    GridView gridview1;
    int [] nature_arr={R.drawable.firstimage,R.drawable.secondimage,R.drawable.thirdimage,R.drawable.fourthimage,R.drawable.fifthimage,R.drawable.firstimage,R.drawable.secondimage,R.drawable.thirdimage,R.drawable.fourthimage,R.drawable.fifthimage};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activityone);

        gridview1=findViewById(R.id.gridview1);
        MyAdaptorOne adaptorOne=new MyAdaptorOne(getApplicationContext(),nature_arr);
        gridview1.setAdapter(adaptorOne);
    }
}