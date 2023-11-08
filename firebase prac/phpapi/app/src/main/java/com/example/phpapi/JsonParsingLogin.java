package com.example.phpapi;

import androidx.annotation.Nullable;
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
import com.example.phpapi.databinding.ActivityJsonParsingLoginBinding;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class JsonParsingLogin extends AppCompatActivity {
    ActivityJsonParsingLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityJsonParsingLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    public void login(View view){
        String email=binding.jlemail.getText().toString();
        String password = binding.jlpassword.getText().toString();

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, urlpaths.jsonLogin_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                Toast.makeText(JsonParsingLogin.this, ""+response, Toast.LENGTH_SHORT).show();

                try {
                    String name=null,email=null,password=null;
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray jsonArray = jsonObject.optJSONArray("response_obj");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                        name = jsonObject1.optString("name").toString();
                        email = jsonObject1.optString("email").toString();
                        password = jsonObject1.optString("password").toString();
                    }
                    Toast.makeText(JsonParsingLogin.this, "Name ==>  "+name+"\nEmail ==>  "+email+"\nPassword ==>  "+password,Toast.LENGTH_SHORT).show();

//                    Toast.makeText(JsonParsingLogin.this, "Hello", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(JsonParsingLogin.this, ""+error, Toast.LENGTH_SHORT).show();

            }
        }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hm=new HashMap<>();
                hm.put("key_email",email);
                hm.put("key_password",password);
                return hm;
            }
        };
        requestQueue.add(stringRequest);
    }
    public void view(View view){
        startActivity(new Intent(this, Activity_get_all_users_data.class));

    }
}