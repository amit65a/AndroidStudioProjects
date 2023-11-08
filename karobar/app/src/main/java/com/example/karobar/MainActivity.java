package com.example.karobar;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv;
    MainAdapter mainadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv=(RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<mainModel> options=
                new FirebaseRecyclerOptions.Builder<mainModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("teacher"),mainModel.class)
                        .build();

        mainadapter =new MainAdapter(options);
        rv.setAdapter(mainadapter);


    }

    @Override
    protected void onStart() {
        super.onStart();
        mainadapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainadapter.stopListening();
    }
}