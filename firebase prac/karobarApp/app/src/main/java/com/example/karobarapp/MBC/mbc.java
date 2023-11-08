package com.example.karobarapp.MBC;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.karobarapp.models.MBCDataModel;
import com.example.karobarapp.R;
import com.example.karobarapp.databinding.ActivityMbcBinding;

public class mbc extends AppCompatActivity {
    ActivityMbcBinding binding;
    MBCDataModel mbcDataModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMbcBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        date();
        read();
        autocomplete();
        submit();

    }


    private void read() {
        binding.readmbc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isConnected(mbc.this)) {
                    Toast.makeText(mbc.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
//                    showCustomDialog();
                } else {
                    startActivity(new Intent(mbc.this, readdata.class));
                }

            }
        });
    }

    private void date() {
        binding.mitimbc.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.length() == 4) {
                    binding.mitimbc.append("-");
                }
                if (s.length() == 7) {
                    binding.mitimbc.append("-");
                }
                if (s.length() == 10) {
                    binding.mitimbc.append("/");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        });
    }

    private void autocomplete() {
        String[] vehicleNumbers = getResources().getStringArray(R.array.vehicleNumber);
        ArrayAdapter vehicleadapter = new ArrayAdapter(this, R.layout.dropdowncustom, vehicleNumbers);
        binding.autocompleteVehiclembc.setAdapter(vehicleadapter);
        String[] tel = getResources().getStringArray(R.array.tel);
        ArrayAdapter teladapter = new ArrayAdapter(this, R.layout.dropdowncustom, tel);
        binding.autocompletetelmbc.setAdapter(teladapter);
    }

    private void submit() {
        binding.submitmbc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isConnected(mbc.this)) {
                    showCustomDialog();
                } else if (!validateMiti() | !validateParinam() | !validateDar() | !validateGadi() | !validateTel()) {
                    return;
                }
                else {
                    String mitimbc = binding.mitimbc.getText().toString();
                    String gadinombc = binding.autocompleteVehiclembc.getText().toString();
                    String telmbc = binding.autocompletetelmbc.getText().toString();
                    String parinammbc = binding.parinammbc.getText().toString();
                    String darmbc = binding.darmbc.getText().toString();
                    String kaifiyatmbc = binding.kaifiyatmbc.getText().toString();

                    if (mitimbc.isEmpty() || parinammbc.isEmpty() || darmbc.isEmpty()) {
                        Toast.makeText(mbc.this, "fill the unfilled", Toast.LENGTH_SHORT).show();
                    } else {
                        mbcDataModel = new MBCDataModel();
                        mbcDataModel.setMiti(mitimbc);
                        mbcDataModel.setGadino(gadinombc);
                        mbcDataModel.setTel(telmbc);
                        mbcDataModel.setParinam(parinammbc);
                        mbcDataModel.setDar(darmbc);
                        mbcDataModel.setKaifiyat(kaifiyatmbc);
                        saveDataapi(mitimbc, gadinombc, telmbc, parinammbc, darmbc, kaifiyatmbc);
                    }
                }
            }

        });

    }

    private boolean validateTel() {
        String tel = binding.autocompletetelmbc.getText().toString();
        if (tel.isEmpty()) {
            binding.autocompletetelmbc.setError("कृपया कुनै एक छनोट गर्नुहोस् ।");
            return false;
        } else {
            binding.autocompletetelmbc.setError(null);
            return true;
        }
    }

    private boolean validateGadi() {
        String gadi = binding.autocompleteVehiclembc.getText().toString();
        if (gadi.isEmpty()) {
            binding.autocompleteVehiclembc.setError("कृपया कुनै एक छनोट गर्नुहोस् ।");
            return false;
        } else {
            binding.autocompleteVehiclembc.setError(null);
            return true;
        }

    }

    private boolean validateDar() {
        String dar = binding.darmbc.getText().toString();
        if (dar.isEmpty()) {
            binding.darmbc.setError("कृपया दर प्रदान गर्नुहोस् ।");
            return false;
        }
        if (Float.parseFloat(dar) == 0) {
            binding.darmbc.setError("दर शून्य हुनसक्दैन ।");
            return false;
        } else {
            binding.darmbc.setError(null);
            return true;
        }
    }

    private boolean validateParinam() {
        String parinam = binding.parinammbc.getText().toString();
        if (parinam.isEmpty()) {
            binding.parinammbc.setError("कृपया परिमाण प्रदान गर्नुहोस् ।");
            return false;
        }
        if (Float.parseFloat(parinam) == 0) {
            binding.parinammbc.setError("परिमाण शून्य हुनसक्दैन ।");
            return false;
        } else {
            binding.parinammbc.setError(null);
            return true;
        }
    }

    private boolean validateMiti() {
        String miti = binding.mitimbc.getText().toString();
        if (miti.isEmpty()) {
            binding.mitimbc.setError("कृपया मिती प्रदान गर्नुहोस् ।");
            return false;
        }
        if (miti.length() != 11) {
            binding.mitimbc.setError("कृपया सही मिती प्रदान गर्नुहोस् ।");
            return false;
        } else {
            binding.mitimbc.setError(null);
            return true;
        }
    }

    private void showCustomDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mbc.this);
        builder.setMessage("Please Connect to the Internet").setCancelable(false).setPositiveButton("connect", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        }).show();
    }

    private boolean isConnected(mbc mbcp) {
        ConnectivityManager connectivityManager = (ConnectivityManager) mbcp.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if ((wifiConn != null && wifiConn.isConnected()) || (mobileConn != null && mobileConn.isConnected())) {
            return true;
        } else {
            return false;
        }
    }

    private void saveDataapi(String mitimbc, String gadinombc, String telmbc, String parinammbc, String darmbc, String kaifiyatmbc) {
        ProgressDialog progressDialog = new ProgressDialog(mbc.this);
        progressDialog.setMessage("Submitting.... Please wait");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        String url = "https://script.google.com/macros/s/AKfycbz_VzLMD50TxgA0GmhcgK2iaVSki2m4zr7MHYHxGPirAHDtBzs8UNo8nIfBOiELNdQazw/exec?" + "action=create&miti=" + mitimbc + "&gadino=" + gadinombc + "&tel=" + telmbc + "&parinam=" + parinammbc + "&dar=" + darmbc + "&kaifiyat=" + kaifiyatmbc;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                Toast.makeText(mbc.this, "Submitted", Toast.LENGTH_SHORT).show();
                binding.autocompleteVehiclembc.setText("");
                binding.autocompletetelmbc.setText("");
                binding.mitimbc.setText("");
                binding.parinammbc.setText("");
                binding.darmbc.setText("");
                binding.kaifiyatmbc.setText("");

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mbc.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(mbc.this);
        queue.add(stringRequest);
    }


}