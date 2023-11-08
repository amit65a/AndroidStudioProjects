package com.example.intentsfile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class implicit_intents extends AppCompatActivity {
    EditText url;
    Button url_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit_intents);

        url=findViewById(R.id.url);
        url_btn=findViewById(R.id.url_btn);

        url_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String link=url.getText().toString();
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                startActivity(intent);
            }
        });
    }
}