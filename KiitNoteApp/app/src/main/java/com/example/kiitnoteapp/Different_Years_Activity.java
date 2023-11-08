package com.example.kiitnoteapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Different_Years_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_different_years);
//        First yaer

        CardView firstyear=findViewById(R.id.cardFirstYear);
        firstyear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(Different_Years_Activity.this, Semsters_Activity.class);
                it.putExtra("title","First Year");
                startActivity(it);
            }
        });
//Second year
        CardView secondyear=findViewById(R.id.cardSecondyear);
        secondyear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(Different_Years_Activity.this, Semsters_Activity.class);
                it.putExtra("title","Second Year");
                startActivity(it);
            }
        });

// Third year
        CardView thirdyear=findViewById(R.id.cardThirdYear);
        thirdyear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(Different_Years_Activity.this, Semsters_Activity.class);
                it.putExtra("title","Third year");
                startActivity(it);
            }
        });
//fourth year
        CardView fouthyear=findViewById(R.id.cardFouthYear);
        fouthyear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(Different_Years_Activity.this, Semsters_Activity.class);
                it.putExtra("title","Fouth Year");
                startActivity(it);
            }
        });


    }
}