package com.example.activity_lifecycle;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class activity extends AppCompatActivity {

    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);
        btn1=findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(activity.this);
                builder.setTitle("Life Cycle");
                builder.setMessage(" Testing activity life cycle (pause state)");
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(activity.this, "Yes clicked", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });
        Log.d("create", "onCreate() method executed");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("start","onStart() method executed");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("resume","onResume() method executed");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("restart","onRestart() method executed");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("pause","onPause() method executed");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("stop","onStop() method executed");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("destory","onDestory() method executed");
    }
}