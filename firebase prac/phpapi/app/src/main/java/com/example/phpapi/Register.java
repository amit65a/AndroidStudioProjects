package com.example.phpapi;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.phpapi.databinding.ActivityRegisterBinding;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    ActivityRegisterBinding binding;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sessionManager =new SessionManager(getApplicationContext());
    }

    public void register(View view){
        String name=binding.rname.getText().toString();
        String email=binding.remail.getText().toString();
        String password=binding.rpassword.getText().toString();

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setTitle("Loading");
        builder.setMessage("Please Wait...");
        AlertDialog alertDialog=builder.create();
        StringRequest stringRequest=new StringRequest(Request.Method.POST, urlpaths.Register_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                sessionManager.createSession(name,email);
                alertDialog.hide();
                Toast.makeText(Register.this, ""+response, Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(Register.this,Login.class);
                startActivity(intent);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                alertDialog.hide();
                Toast.makeText(Register.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap=new HashMap<>();
                hashMap.put("key_name",name);
                hashMap.put("key_email",email);
                hashMap.put("key_password",password);
                return hashMap;
            }
        };
        requestQueue.add(stringRequest);
    }
}