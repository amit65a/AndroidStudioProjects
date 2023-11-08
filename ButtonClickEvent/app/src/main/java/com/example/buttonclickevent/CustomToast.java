package com.example.buttonclickevent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CustomToast extends AppCompatActivity {
    EditText mail;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_toast);
        mail=findViewById(R.id.et_email);
    }
    public void opencustomtoast(View view){
        LayoutInflater lf=getLayoutInflater();
        View layout=lf.inflate(R.layout.toast_design,(ViewGroup) findViewById(R.id.customtoast));
        tv=(TextView) layout.findViewById(R.id.view1);

        String email=mail.getText().toString();
        tv.setText(email);

        Toast t=new Toast(getApplicationContext());
        t.setView(layout);
        t.setGravity(Gravity.CENTER,0,-500);
        t.setDuration(Toast.LENGTH_LONG);
        t.show();

    }
}