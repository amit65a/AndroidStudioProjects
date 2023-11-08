package com.example.phpapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Activity_get_all_users_data extends AppCompatActivity {

    RecyclerView rv_data;
    ArrayList allName,allEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_all_users_data);
        rv_data = findViewById(R.id.rv_data);

        allName=new ArrayList();
        allEmail=new ArrayList();
        getUserData();

    }

    void getUserData(){
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, urlpaths.jsonAllUser_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.equals("fail"))
                    Toast.makeText(Activity_get_all_users_data.this, "Error Occured", Toast.LENGTH_SHORT).show();
                else {
                    String name,email;
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            JSONArray jsonArray = jsonObject.optJSONArray("response_obj");

                            for (int i=0;i<jsonArray.length();i++){
                                JSONObject jsonObject1=jsonArray.getJSONObject(i);
                                name=jsonObject1.optString("name");
                                email=jsonObject1.optString("email");
                                allName.add(name);
                                allEmail.add(email);
                            }
                            Myadapter myadapter=new Myadapter(allName,allEmail);
                            rv_data.setLayoutManager(new LinearLayoutManager(Activity_get_all_users_data.this));
                            rv_data.setAdapter(myadapter);
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Activity_get_all_users_data.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(stringRequest);

    }
}