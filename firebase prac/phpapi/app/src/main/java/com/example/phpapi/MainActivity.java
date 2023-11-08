package com.example.phpapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SessionManager sessionManager=new SessionManager(getApplicationContext());
        boolean b=sessionManager.checkSession();
        if (b==true){
            Intent intent=new Intent(this,Profile.class);
            startActivity(intent);
        }
    }
    public void Register(View view){
        Intent intent=new Intent(this,Register.class);
        startActivity(intent);
    }
    public void Login(View view){
        Intent intent=new Intent(this,Login.class);
        startActivity(intent);
    }
    public void JsonLogin(View view){
        Intent intent=new Intent(this,JsonParsingLogin.class);
        startActivity(intent);
    }
}