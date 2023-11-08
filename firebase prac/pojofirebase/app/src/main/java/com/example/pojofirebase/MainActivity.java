package com.example.pojofirebase;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pojofirebase.model.User;
import com.example.pojofirebase.model.userAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editname,editage;
    private Button create,read,update,delete;
    private TextView display;

    private FirebaseDatabase mdatabase;
    private DatabaseReference dref;

    private ChildEventListener mchildListener;

    private RecyclerView recyclerView;
    private userAdapter userAdapt;
    private List<User>mdatalist;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        editname=findViewById(R.id.editname);
        editage=findViewById(R.id.editage);
        create=findViewById(R.id.btncreate);
        read=findViewById(R.id.btnread);
        update=findViewById(R.id.btnupdate);
        delete=findViewById(R.id.btndelete);
        display=findViewById(R.id.display);
        recyclerView=findViewById(R.id.rview);

        mdatabase=FirebaseDatabase.getInstance();
        dref=mdatabase.getReference("users");
        create();
//        read();

        mdatalist=new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        userAdapt=new userAdapter(this,mdatalist);

        recyclerView.setAdapter(userAdapt);

        mchildListener=new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                User user=snapshot.getValue(User.class);
                user.setUid(snapshot.getKey());


                mdatalist.add(user);
                userAdapt.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                User user=snapshot.getValue(User.class);
                user.setUid(snapshot.getKey());
                mdatalist.remove(user);
                userAdapt.notifyDataSetChanged();

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        dref.addChildEventListener(mchildListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dref.removeEventListener(mchildListener);
    }

    private void create(){
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=editname.getText().toString();
                int age=Integer.parseInt(editage.getText().toString());
                User user=new User(name,age);
                String key=dref.push().getKey();
                dref.child(key).setValue(user);
                Toast.makeText(MainActivity.this, "Value inserted ...", Toast.LENGTH_SHORT).show();
            }
        });

    }

//    private void read(){
//        this.create=findViewById(R.id.btncreate);
//        this.read=findViewById(R.id.btnread);
//        this.editname=findViewById(R.id.editname);
//        this.editage=findViewById(R.id.editage);
//        this.display=findViewById(R.id.display);
//
//    }
}