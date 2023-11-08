package com.example.karobarapp.Kharid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.karobarapp.Adapters.KharidAdapter;
import com.example.karobarapp.Adapters.OthersAdapter;
import com.example.karobarapp.Others.readDataOthers;
import com.example.karobarapp.R;
import com.example.karobarapp.models.KharidDataModel;
import com.example.karobarapp.models.OthersDataModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.List;

public class readDataKharid extends AppCompatActivity {

    KharidAdapter kharidAdapter;
    private static final String url = "https://script.google.com/macros/s/AKfycbwl1JQ_XFisszYI8louFaRUKYIss6Cx0dJwONrTvcNh3cZGdq9rDe-ug3FBA9XKFyxxmA/exec"+"?action=getkharid";

    RecyclerView recyclerViewKharid;
    TextView totalkharid;
    Button clickkharid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_data_kharid);

        recyclerViewKharid = findViewById(R.id.recyclerViewKharid);
        totalkharid=findViewById(R.id.totalkharid);
        clickkharid=findViewById(R.id.clickkharid);

        jsonparse();

        kharidAdapter = new KharidAdapter(this);
        recyclerViewKharid.setAdapter(kharidAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerViewKharid.setLayoutManager(layoutManager);


        clickkharid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float sumparinam = 0, sumtotal = 0;
                int count = 0;
                if (kharidAdapter.getSelected().size() > 0) {
                    StringBuilder stringBuilderparinam = new StringBuilder();
                    StringBuilder stringBuildertotal = new StringBuilder();
                    for (int i = 0; i < kharidAdapter.getSelected().size(); i++) {
                        count++;
                        stringBuilderparinam.replace(0, 30, kharidAdapter.getSelected().get(i).getParinamk());
                        stringBuildertotal.replace(0, 30, kharidAdapter.getSelected().get(i).getDark());
                        sumtotal = sumtotal + (Float.parseFloat(stringBuilderparinam.toString()) * (Float.parseFloat(stringBuildertotal.toString())));
                        sumparinam = sumparinam + Float.parseFloat(stringBuilderparinam.toString());
                    }
                } else {
                    ShowToast("No selection");
                }
                ShowToast("तेल => " + sumparinam + "\nरकम => " + sumtotal+"\nSelected => "+count);

            }
        });
    }

    private void ShowToast(String msg) {
        totalkharid.setText(msg);
    }

    private void jsonparse() {

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading.... Please wait");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("items");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        String idk = object.getString("id");
                        String mitik = object.getString("miti");
                        String telk = object.getString("tel");
                        String batak = object.getString("bata");
                        String parinamk = object.getString("parinam");
                        String dark = object.getString("dar");
                        String kaifiyatk = object.getString("kaifiyat");
                        KharidDataModel kharidDataModel = new KharidDataModel(idk, mitik, telk, batak, parinamk, dark, kaifiyatk);
                        kharidAdapter.addModel(kharidDataModel);
                    }
                    progressDialog.dismiss();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(readDataKharid.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(readDataKharid.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonObjectRequest);
        }
}