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
import com.example.phpapi.databinding.ActivityLoginBinding;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    ActivityLoginBinding binding;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sessionManager=new SessionManager(getApplicationContext());
    }

    public void login(View view){
        String email=binding.lemail.getText().toString();
        String password = binding.lpassword.getText().toString();

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setTitle("Loading");
        builder.setMessage("Please Wait...");
        AlertDialog alertDialog=builder.create();
        StringRequest stringRequest=new StringRequest(Request.Method.POST, urlpaths.Login_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                alertDialog.hide();
                if (response.trim().equals("Fail")) {
                    Toast.makeText(Login.this, "Email id and Password didn't match", Toast.LENGTH_SHORT).show();
                }
                else {
                    sessionManager.createSession(email,"");
                    Toast.makeText(Login.this, ""+response, Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(Login.this,Profile.class);
                    startActivity(intent);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                alertDialog.hide();
                Toast.makeText(Login.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap=new HashMap<>();
                hashMap.put("key_email",email);
                hashMap.put("key_password",password);
                return hashMap;
            }
        };
        requestQueue.add(stringRequest);

    }
}
