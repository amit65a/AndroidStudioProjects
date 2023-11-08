package com.example.karobarapp.MBC;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
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
import com.example.karobarapp.R;
import com.example.karobarapp.databinding.ActivityUpdatembcBinding;
import com.example.karobarapp.models.MBCDataModel;

public class updatembc extends AppCompatActivity {

    ActivityUpdatembcBinding binding;
    AlertDialog.Builder builder;
    private MBCDataModel mbcDataModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdatembcBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mbcDataModel = (MBCDataModel) getIntent().getSerializableExtra("model");


        binding.mitimbcupdate.setText(mbcDataModel.getMiti());
        binding.autocompleteVehiclembcupdate.setText(mbcDataModel.getGadino());
        binding.autocompletetelmbcupdate.setText(mbcDataModel.getTel());
        binding.parinammbcupdate.setText(mbcDataModel.getParinam());
        binding.darmbcupdate.setText(mbcDataModel.getDar());
        binding.kaifiyatmbcupdate.setText(mbcDataModel.getKaifiyat());
        date();
        autocomplete();
        update();
    }

    private void showCustomDialog() {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(updatembc.this);
        builder.setMessage("No Internet Connection! Please Connect to the Internet").setCancelable(false).setPositiveButton("connect", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(getApplicationContext(), mbc.class));
                finish();
            }
        }).show();
    }

    private void date() {
        binding.mitimbcupdate.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.length() == 4) {
                    binding.mitimbcupdate.append("-");
                }
                if (s.length() == 7) {
                    binding.mitimbcupdate.append("-");
                }
                if (s.length() == 10) {
                    binding.mitimbcupdate.append("/");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        });
    }

    private void update() {
        binding.updatembc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isConnected(updatembc.this)) {
                    showCustomDialog();
                } else if (!validateMiti() | !validateParinam() | !validateDar() | !validateGadi() | !validateTel()) {
                    return;
                } else {
                    builder = new AlertDialog.Builder(updatembc.this);
                    builder.setTitle("Alert").setMessage("Are you sure to update ?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String mitimbcupdate = binding.mitimbcupdate.getText().toString();
                            String gadinombcupdate = binding.autocompleteVehiclembcupdate.getText().toString();
                            String telmbcupdate = binding.autocompletetelmbcupdate.getText().toString();
                            String parinammbcupdate = binding.parinammbcupdate.getText().toString();
                            String darmbcupdate = binding.darmbcupdate.getText().toString();
                            String kaifiyatmbcupdate = binding.kaifiyatmbcupdate.getText().toString();

                            mbcDataModel.setMiti(mitimbcupdate);
                            mbcDataModel.setGadino(gadinombcupdate);
                            mbcDataModel.setTel(telmbcupdate);
                            mbcDataModel.setParinam(parinammbcupdate);
                            mbcDataModel.setDar(darmbcupdate);
                            mbcDataModel.setKaifiyat(kaifiyatmbcupdate);

                            updatedata(mitimbcupdate, gadinombcupdate, telmbcupdate, parinammbcupdate, darmbcupdate, kaifiyatmbcupdate);

                        }
                    }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    }).show();


                }
            }
        });
    }


    private boolean validateTel() {
        String tel = binding.autocompletetelmbcupdate.getText().toString();
        if (tel.isEmpty()) {
            binding.autocompletetelmbcupdate.setError("कृपया केही छनोट गर्नुहोस् ।");
            return false;
        } else {
            binding.autocompletetelmbcupdate.setError(null);
            return true;
        }
    }

    private boolean validateGadi() {
        String gadi = binding.autocompleteVehiclembcupdate.getText().toString();
        if (gadi.isEmpty()) {
            binding.autocompleteVehiclembcupdate.setError("कृपया केही छनोट गर्नुहोस् ।");
            return false;
        } else {
            binding.autocompleteVehiclembcupdate.setError(null);
            return true;
        }

    }

    private boolean validateDar() {
        String dar = binding.darmbcupdate.getText().toString();
        if (dar.isEmpty()) {
            binding.darmbcupdate.setError("कृपया दर प्रदान गर्नुहोस् ।");
            return false;
        }
        if (Float.parseFloat(dar) == 0) {
            binding.darmbcupdate.setError("दर शून्य हुनसक्दैन ।");
            return false;
        } else {
            binding.darmbcupdate.setError(null);
            return true;
        }
    }

    private boolean validateParinam() {
        String parinam = binding.parinammbcupdate.getText().toString();
        if (parinam.isEmpty()) {
            binding.parinammbcupdate.setError("कृपया परिमाण प्रदान गर्नुहोस् ।");
            return false;
        }
        if (Float.parseFloat(parinam) == 0) {
            binding.parinammbcupdate.setError("परिमाण शून्य हुनसक्दैन ।");
            return false;
        } else {
            binding.parinammbcupdate.setError(null);
            return true;
        }
    }

    private boolean validateMiti() {
        String miti = binding.mitimbcupdate.getText().toString();
        if (miti.isEmpty()) {
            binding.mitimbcupdate.setError("कृपया मिती प्रदान गर्नुहोस् ।");
            return false;
        }
        if (miti.length() != 11) {
            binding.mitimbcupdate.setError("कृपया सही मिती प्रदान गर्नुहोस् ।\n");
            return false;
        } else {
            binding.mitimbcupdate.setError(null);
            return true;
        }
    }

    private void autocomplete() {
        String[] vehicleNumbers = getResources().getStringArray(R.array.vehicleNumber);
        ArrayAdapter vehicleadapter = new ArrayAdapter(this, R.layout.dropdowncustom, vehicleNumbers);
        binding.autocompleteVehiclembcupdate.setAdapter(vehicleadapter);
        String[] tel = getResources().getStringArray(R.array.tel);
        ArrayAdapter teladapter = new ArrayAdapter(this, R.layout.dropdowncustom, tel);
        binding.autocompletetelmbcupdate.setAdapter(teladapter);
    }

    private boolean isConnected(updatembc umbc) {
        ConnectivityManager connectivityManager = (ConnectivityManager) umbc.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if ((wifiConn != null && wifiConn.isConnected()) || (mobileConn != null && mobileConn.isConnected())) {
            return true;
        } else {
            return false;
        }
    }

    private void updatedata(String mitimbc, String gadinombc, String telmbc, String parinammbc, String darmbc, String kaifiyatmbc) {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating.... Please wait");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        String url = "https://script.google.com/macros/s/AKfycbz_VzLMD50TxgA0GmhcgK2iaVSki2m4zr7MHYHxGPirAHDtBzs8UNo8nIfBOiELNdQazw/exec" + "?action=update&id=" + mbcDataModel.getId() + "&miti="
                + mbcDataModel.getMiti() + "&gadino=" + mbcDataModel.getGadino() + "&tel=" + mbcDataModel.getTel() + "&parinam=" + mbcDataModel.getParinam() + "&dar=" + mbcDataModel.getDar() + "&kaifiyat=" + mbcDataModel.getKaifiyat();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(updatembc.this, response, Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                startActivity(new Intent(updatembc.this, mbc.class));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(updatembc.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);

    }
}