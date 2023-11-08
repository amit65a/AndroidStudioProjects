package com.example.buttonclickevent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText edit_text;
    Button btn;
    TextView text_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit_text=findViewById(R.id.et_name);
        btn=findViewById(R.id.btn_one);
        text_view=findViewById(R.id.text_view);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=edit_text.getText().toString();
                text_view.setText("Name : "+name);
            }
        });
    }
}