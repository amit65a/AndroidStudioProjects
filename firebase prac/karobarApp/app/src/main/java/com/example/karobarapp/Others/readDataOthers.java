package com.example.karobarapp.Others;

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
import com.example.karobarapp.Adapters.MbcAdapter;
import com.example.karobarapp.Adapters.OthersAdapter;
import com.example.karobarapp.MBC.readdata;
import com.example.karobarapp.R;
import com.example.karobarapp.models.MBCDataModel;
import com.example.karobarapp.models.OthersDataModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class readDataOthers extends AppCompatActivity {
    OthersAdapter othersAdapter;
    private List<OthersDataModel> dataModelList;
    TextView totalothers;
    Button clickothers;
    private static final String url = "https://script.google.com/macros/s/AKfycbwl1JQ_XFisszYI8louFaRUKYIss6Cx0dJwONrTvcNh3cZGdq9rDe-ug3FBA9XKFyxxmA/exec" + "?action=getothers";
    RecyclerView recyclerViewOthers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_data_others);

        recyclerViewOthers = findViewById(R.id.recyclerViewOthers);
        totalothers = findViewById(R.id.totalothers);
        clickothers = findViewById(R.id.clickothers);

        jsonparse();

        othersAdapter = new OthersAdapter(this);
        recyclerViewOthers.setAdapter(othersAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerViewOthers.setLayoutManager(layoutManager);


        clickothers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float sumparinam = 0, sumtotal = 0;
                int count = 0;
                if (othersAdapter.getSelected().size() > 0) {
                    StringBuilder stringBuilderparinam = new StringBuilder();
                    StringBuilder stringBuildertotal = new StringBuilder();
                    for (int i = 0; i < othersAdapter.getSelected().size(); i++) {
                        count++;
                        stringBuilderparinam.replace(0, 30, othersAdapter.getSelected().get(i).getParinamo());
                        stringBuildertotal.replace(0, 30, othersAdapter.getSelected().get(i).getDaro());
                        sumtotal = sumtotal + (Float.parseFloat(stringBuilderparinam.toString()) * (Float.parseFloat(stringBuildertotal.toString())));
                        sumparinam = sumparinam + Float.parseFloat(stringBuilderparinam.toString());
                    }
                } else {
                    ShowToast("No selection");
                }
                ShowToast("तेल => " + sumparinam + "\nरकम => " + sumtotal + "\nSelected => " + count);

            }
        });
    }

    private void ShowToast(String msg) {
        totalothers.setText(msg);
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
                        String ido = object.getString("id");
                        String mitio = object.getString("miti");
                        String telo = object.getString("tel");
                        String karobaro = object.getString("karobar");
                        String parinamo = object.getString("parinam");
                        String daro = object.getString("dar");
                        String kaifiyato = object.getString("kaifiyat");
                        OthersDataModel othersDataModel = new OthersDataModel(ido, mitio, telo, karobaro, parinamo, daro, kaifiyato);
                        othersAdapter.addModel(othersDataModel);
                    }
                    progressDialog.dismiss();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(readDataOthers.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(readDataOthers.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonObjectRequest);


    }
}