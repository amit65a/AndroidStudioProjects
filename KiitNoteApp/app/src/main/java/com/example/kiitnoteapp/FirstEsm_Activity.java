package com.example.kiitnoteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;

public class FirstEsm_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_esm);



//        //        for physics
       Button btnphysics=findViewById(R.id.buttonPhysics);
        btnphysics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri=Uri.parse("https://google.com/drive/folders/13g4E2MD3_5J3EKPTVbq5Y8bRNQkqg5iv");
                Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });
//
//
//
//        //        for Maths
//        Button btnmaths=findViewById(R.id.buttonMaths);
//        btnmaths.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent it=new Intent(FirstEsm_Activity.this, Material_Details_Activity.class);
//                it.putExtra("title","Maths");
//                startActivity(it);
//            }
//        });
//
//
//        //        for Biology
//        Button btnbiology=findViewById(R.id.buttonBiology);
//        btnbiology.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent it=new Intent(FirstEsm_Activity.this, Material_Details_Activity.class);
//                it.putExtra("title","Biology");
//                startActivity(it);
//            }
//        });
//
//
//
//        //        for Eng.Mechanic
//        Button btnEngmec=findViewById(R.id.buttonEngMechanic);
//        btnEngmec.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent it=new Intent(FirstEsm_Activity.this, Material_Details_Activity.class);
//                it.putExtra("title","Eng Mechanic");
//                startActivity(it);
//            }
//        });
//
//
//        //        for Nano science
//        Button btnnano=findViewById(R.id.buttonNano);
//        btnnano.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent it=new Intent(FirstEsm_Activity.this, Material_Details_Activity.class);
//                it.putExtra("title","Nano Science");
//                startActivity(it);
//            }
//        });
//
//
//
//        //        for EVS
//        Button btnEvs=findViewById(R.id.buttonEvs);
//        btnEvs.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent it=new Intent(FirstEsm_Activity.this, Material_Details_Activity.class);
//                it.putExtra("title","Evs");
//                startActivity(it);
//            }
//        });

    }
}