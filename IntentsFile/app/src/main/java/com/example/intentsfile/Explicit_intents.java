package com.example.intentsfile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Explicit_intents extends AppCompatActivity {
    TextView textfirst;
    Button btnfirst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_);

        textfirst=findViewById(R.id.textfirst);
        btnfirst=findViewById(R.id.btnfirst);

        btnfirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Second_Activity.class);

                intent.putExtra("key_name","Hari");
                intent.putExtra("key_roll","21053445");
                intent.putExtra("key_section","CSE 14");
                intent.putExtra("key_number","8260271265");

                startActivity(intent);
            }
        });
    }
}