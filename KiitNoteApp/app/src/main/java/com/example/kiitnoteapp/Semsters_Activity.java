package com.example.kiitnoteapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Semsters_Activity extends AppCompatActivity {

    private String[][] semester_details1={
            {"FIRST SEMESTER"},{"Second Semester"}
    };

    private String[][] semester_details2={
            {"THIRD SEMESTER"},{"FOURTH SEMESTER"}
    };
    private String[][] semester_details3={
            {"FIFTH SEMESTER"},{"SIXTH SEMESTER"}
    };
    private String[][] semester_details4={
            {"SEVENTH SEMESTER"},{"EIGHTH SEMESTER"}
    };

    TextView tv;
    TextView text1,text2;



    String [][] semester_deatils={};

    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semsters);
        tv=findViewById(R.id.textViewSemTitle);
        text1=findViewById(R.id.text1);
        text2=findViewById(R.id.text2);
        CardView cd=findViewById(R.id.cardSemester1);
        CardView cd2=findViewById(R.id.cardSemester2);

        Intent it=getIntent();
        String title= it.getStringExtra("title");
        tv.setText(title);

        if(title.compareTo("First Year")==0) {
            semester_deatils = semester_details1;
            text1.setText(" Sem 1 Scheme I and sem 2 Scheme II");
            cd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(Semsters_Activity.this, FirstEsm_Activity.class));

                }
            });

            text2.setText("Sem 2 Scheme I and Sem 1 Scheme II");
            cd2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(Semsters_Activity.this, Second_Sem_Activity.class));
                }
            });
        }

        else if(title.compareTo("Second Year")==0) {
            semester_deatils = semester_details2;
            text1.setText("Third semester");
            cd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(Semsters_Activity.this, Third_Sem_Activity.class));
                }
            });
            text2.setText("Fourth semester");
            cd2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(Semsters_Activity.this, Fourth_Sem_Activity.class));
                }
            });
        }

        else if(title.compareTo("Third Year")==0) {
            semester_deatils = semester_details3;
            text1.setText("Fifth semester");
            cd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(Semsters_Activity.this, Fifth_Sem_Activity.class));
                }
            });
            text2.setText("Sixth semester");
            cd2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(Semsters_Activity.this, Sixth_Sem_Activity.class));
                }
            });
        }
        else {
            semester_deatils = semester_details4;
            text1.setText("Seventh semester");
            cd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(Semsters_Activity.this,Seventh_sem_Activity.class));
                }
            });
            text2.setText("Eighth semester");
            cd2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(Semsters_Activity.this, Eight_sem_Activity.class));
                }
            });
        }







    }
}