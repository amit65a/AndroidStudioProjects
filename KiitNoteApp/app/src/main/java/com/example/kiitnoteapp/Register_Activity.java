package com.example.kiitnoteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register_Activity extends AppCompatActivity {
    EditText edUsername,edEmail,edPassword,edConfirmPassword;
    Button btn;
//    Database DB;
    FirebaseAuth mAuth;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            startActivity(new Intent(Register_Activity.this,HomeActivity.class));


        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edEmail=findViewById(R.id.editTextRegEmail);
        edPassword=findViewById(R.id.editTextRegPassword);
        edConfirmPassword=findViewById(R.id.editTextRegConfirmPassword);
        btn=findViewById(R.id.buttonRegister);
//        DB=new Database(this);
        mAuth= FirebaseAuth.getInstance();


        // working for registerr button
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=edUsername.getText().toString();
                String email=edEmail.getText().toString();
                String password=edPassword.getText().toString();
                String confirm=edConfirmPassword.getText().toString();






                if(username.length()==0 || email.length()==0|| password.length()==0|| confirm.length()==0){
                    Toast.makeText(getApplicationContext(),"Please fill All the details",Toast.LENGTH_SHORT).show();
                }

                else{
                    if(password.compareTo(confirm)==0) {
                        //calling the function to check valid password
                        if(isValid(password)){
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Password must contain at least 6 character having letter,digit and special character", Toast.LENGTH_SHORT).show();
                        }



                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Password and confirm password does not match", Toast.LENGTH_SHORT).show();
                    }
                }

                //code for firebase auticantor
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(Register_Activity.this, "Account Created.",
                                            Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(Register_Activity.this, HomeActivity.class));
//                                    finish();


                                } else {
                                    // If sign in fails, display a message to the user.

                                    Toast.makeText(Register_Activity.this, "please provide correct Details.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });



            }
        });

    }


    public static boolean isValid(String passwordhere){
        int f1=0,f2=0,f3=0;
        if(passwordhere.length()<6){
            return false;
        }
        else{
            for(int p=0;p<passwordhere.length();p++){
                if(Character.isLetter(passwordhere.charAt(p))){
                    f1=1;
                }
            }

            for(int r=0;r<passwordhere.length();r++){
                if(Character.isDigit(passwordhere.charAt(r))){
                    f2=1;
                }
            }

            for(int s=0;s<passwordhere.length();s++){
                char c=passwordhere.charAt(s);
                if(c>=33 && c<=46 || c==64){
                    f3=1;
                }
            }

            if(f1==1&&f2==1&&f3==1){
                return true;
            }
            return false;
        }
    }
}