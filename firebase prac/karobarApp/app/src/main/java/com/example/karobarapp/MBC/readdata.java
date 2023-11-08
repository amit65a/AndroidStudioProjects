package com.example.karobarapp.MBC;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.karobarapp.Adapters.MbcAdapter;
import com.example.karobarapp.R;
import com.example.karobarapp.models.MBCDataModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class readdata extends AppCompatActivity {
    ArrayList<MBCDataModel> mbcDataModels;
    MbcAdapter mbcAdapter;
    private static final String url = "https://script.google.com/macros/s/AKfycbwl1JQ_XFisszYI8louFaRUKYIss6Cx0dJwONrTvcNh3cZGdq9rDe-ug3FBA9XKFyxxmA/exec" + "?action=get";
    RecyclerView recyclerView;
    TextView totalmbc;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_readdata);
        recyclerView = findViewById(R.id.recyclerView);
        totalmbc = findViewById(R.id.totalmbc);
        btn = findViewById(R.id.click);

        jsonparse();

        mbcAdapter = new MbcAdapter(this, mbcDataModels);
        recyclerView.setAdapter(mbcAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float sumparinam = 0, sumtotal = 0;
                int count = 0;
                if (mbcAdapter.getSelected().size() > 0) {
                    StringBuilder stringBuilderparinam = new StringBuilder();
                    StringBuilder stringBuildertotal = new StringBuilder();
                    for (int i = 0; i < mbcAdapter.getSelected().size(); i++) {
                        count++;
                        stringBuilderparinam.replace(0, 30, mbcAdapter.getSelected().get(i).getParinam());
                        stringBuildertotal.replace(0, 30, mbcAdapter.getSelected().get(i).getDar());
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
        totalmbc.setText(msg);
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
                        String id = object.getString("id");
                        String miti = object.getString("miti");
                        String gadino = object.getString("gadino");
                        String tel = object.getString("tel");
                        String parinam = object.getString("parinam");
                        String dar = object.getString("dar");
                        String kaifiyat = object.getString("kaifiyat");
                        MBCDataModel mbcDataModel = new MBCDataModel(id, miti, gadino, tel, parinam, dar, kaifiyat);
                        mbcAdapter.addModel(mbcDataModel);
                    }
                    progressDialog.dismiss();
                    MBCDataModel mbcDataModel = new MBCDataModel();
                    mbcDataModel.setChecked(true);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(readdata.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(readdata.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonObjectRequest);


    }

}