package com.example.karobarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.karobarapp.MBC.mbc;
import com.example.karobarapp.Others.others;

public class bikri extends AppCompatActivity {
    Button mbcbtn, othersbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bikri);

        mbcbtn = findViewById(R.id.mbc);
        othersbtn = findViewById(R.id.others);
        listener();

    }

    private void listener() {
        mbcbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mbcintent = new Intent(bikri.this, mbc.class);
                startActivity(mbcintent);
            }
        });
        othersbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent othersintent = new Intent(bikri.this, others.class);
                startActivity(othersintent);
            }
        });
    }

}