package com.example.firebasep;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class user extends AppCompatActivity {

    private TextView textView;
    private EditText editText,editText2;
    private Button button;
    private  Button readbutton;
    private Button buttonupdate;
    private Button buttondelete;

    FirebaseDatabase mdatabase;
    DatabaseReference dref;
//    ValueEventListener mlistener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=findViewById(R.id.textView);
        editText=findViewById(R.id.editText);
        editText2=findViewById(R.id.editText2);

        button=findViewById(R.id.button);
        readbutton=findViewById(R.id.readbutton);


        mdatabase=FirebaseDatabase.getInstance();
        dref=mdatabase.getReference("users");

        run();
//        update();
        read();

        /*
        mlistener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String t=(String) snapshot.getValue();
                textView.setText(t);
                Log.d(TAG, "onDataChange: Name"+t);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        dref.child("user1").addValueEventListener(mlistener);
*/

    }

    private void update(){
        buttonupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=editText.getText().toString();
                int age=Integer.parseInt(editText2.getText().toString());

                Map<String,Object> updated=new HashMap<>();
                updated.put("/user1/Name",name);
                updated.put("user1/Age",age);
                dref.updateChildren(updated);

            }
        });
    }

    private void delete(){
        buttondelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Task<Void> t=dref.child("user1").removeValue();
                t.addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(user.this, "Data removed", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(user.this, "Data failed to remove", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }


//    private void run(){
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String name=editText.getText().toString();
//                String key=dref.push().getKey();
//                int age=Integer.parseInt(editText2.getText().toString());
//
//
//
//                dref.child(key).child("Name").setValue(name).addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void unused) {
//                        Toast.makeText(MainActivity.this, "Value Inserted....", Toast.LENGTH_SHORT).show();
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//
//                        Toast.makeText(MainActivity.this, "Fail to Insert....", Toast.LENGTH_SHORT).show();
//
//                    }
//                });
//
//                dref.child(key).child("Age").setValue(age);
//
//            }
//        });
//    }
//
//    private void read(){
//        readbutton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dref.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        int sum=0;
//
//                        for (DataSnapshot snapsho:snapshot.getChildren()) {
//
//                            Map<String,Object> t= (Map<String, Object>) snapsho.getValue();
////                        String t=(String) snapshot.getValue();
//                            textView.setText(t.get("Name").toString()+" "+t.get("Age").toString());
//                            Log.d(TAG, "datachange Name : "+t.get("Name"));
//                            Log.d(TAG, "datachange Age : "+t.get("Age"));
//                            sum=sum+Integer.parseInt(t.get("Age").toString());
//                        }
//                        Toast.makeText(MainActivity.this, "The total age is "+sum, Toast.LENGTH_SHORT).show();
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//                        Toast.makeText(MainActivity.this, "Data Error", Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//            }
//        });
//    }
//





    //By using Child Event Listener

    private void run(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=editText.getText().toString();
                String key=dref.push().getKey();
                int age=Integer.parseInt(editText2.getText().toString());

                Map<String,Object> insertvalues=new HashMap<>();
                insertvalues.put("Name",name);
                insertvalues.put("Age",age);

                dref.child(key).setValue(insertvalues);

                Toast.makeText(user.this, "Values inserted", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void read(){
        readbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dref.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        Map<String,Object> data=(Map<String, Object>) snapshot.getValue();

                        Log.d(TAG, "onChildAdded: Name "+data.get("Name"));
                        Log.d(TAG, "onChildAdded: Age "+data.get("Age"));
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
    }


}