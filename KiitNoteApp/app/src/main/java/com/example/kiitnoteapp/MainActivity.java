package com.example.kiitnoteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
Button regbtn,lgbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        regbtn=findViewById(R.id.button_Register);
        lgbtn=findViewById(R.id.button_Login);


        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Register_Activity.class));
                Toast.makeText(getApplicationContext(), "Welcome", Toast.LENGTH_SHORT).show();

            }
        });

        lgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Login_Activity.class));
                Toast.makeText(getApplicationContext(), "Welcome", Toast.LENGTH_SHORT).show();

            }
        });

    }
}