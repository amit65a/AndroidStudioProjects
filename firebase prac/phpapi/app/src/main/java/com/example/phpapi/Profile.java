package com.example.phpapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Profile extends AppCompatActivity {
    SessionManager sessionManager;
    TextView name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        sessionManager=new SessionManager(getApplicationContext());

        name=findViewById(R.id.name);
        String nam=sessionManager.getDetails("key_session_name");
        name.setText(nam);
    }
    public void logouts(View view){
        sessionManager.logoutSession();
    }
}