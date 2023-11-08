package com.example.kiitnoteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SGPA_Login_Activity extends AppCompatActivity {
Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sgpa_login);
        btn1=findViewById(R.id.button4);
        btn2=findViewById(R.id.button5);
        btn3=findViewById(R.id.button6);
        btn4=findViewById(R.id.button7);
        btn5=findViewById(R.id.button8);
        btn6=findViewById(R.id.button9);
        btn7=findViewById(R.id.button10);
        btn8=findViewById(R.id.button11);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SGPA_Login_Activity.this, GPA_CalCulation_Activity.class));
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SGPA_Login_Activity.this, First_Sem_scheme2_Activity.class));
            }
        });


        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SGPA_Login_Activity.this, Second_sem_cgpa_Activity.class));
            }
        });


        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SGPA_Login_Activity.this, Second_Sem_scheme2_Activity.class));
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SGPA_Login_Activity.this, Third_Sem_Gpa_Activity.class));
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SGPA_Login_Activity.this, Fouth_sem_gpa_Activity.class));
            }
        });


        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SGPA_Login_Activity.this, Fifth_sem_gpa_Activity.class));
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SGPA_Login_Activity.this, six_sem_gpa_Activity.class));
            }
        });

    }
}