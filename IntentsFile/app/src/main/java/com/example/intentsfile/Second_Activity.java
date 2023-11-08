package com.example.intentsfile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Second_Activity extends AppCompatActivity {
    TextView textsecond;
    Button btnsecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textsecond=findViewById(R.id.textsecond);
        btnsecond=findViewById(R.id.btnsecond);

        Bundle bundle=getIntent().getExtras();
        String name=bundle.getString("key_name");
        String rollno=bundle.getString("key_roll");
        String Section=bundle.getString("key_section");
        String no=bundle.getString("key_number");

        Toast.makeText(this, "Name = "+name+"  Rollno = "+rollno+"  Section = "+Section+"  Number = "+no, Toast.LENGTH_SHORT).show();


//        btnsecond.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(getApplicationContext(),Explicit_intents.class);
//                startActivity(intent);
//            }
//        });
    }
}