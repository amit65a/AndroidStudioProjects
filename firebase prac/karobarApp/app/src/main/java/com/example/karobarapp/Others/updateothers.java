package com.example.karobarapp.Others;

import static java.lang.Double.parseDouble;

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
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.karobarapp.Kharid.kharid;
import com.example.karobarapp.Kharid.updatekharid;
import com.example.karobarapp.MBC.mbc;
import com.example.karobarapp.MBC.updatembc;
import com.example.karobarapp.R;
import com.example.karobarapp.databinding.ActivityUpdatembcBinding;
import com.example.karobarapp.databinding.ActivityUpdateothersBinding;
import com.example.karobarapp.models.MBCDataModel;
import com.example.karobarapp.models.OthersDataModel;

import java.text.DecimalFormat;

public class updateothers extends AppCompatActivity {
    ActivityUpdateothersBinding binding;
    RadioButton radioButton;
    AlertDialog.Builder builder;
    private OthersDataModel othersDataModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateothersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        othersDataModel = (OthersDataModel) getIntent().getSerializableExtra("modelothers");


        binding.mitioupdate.setText(othersDataModel.getMitio());
        int id = binding.karobarupdate.getCheckedRadioButtonId();
        binding.karobarupdate.setId(id);
        binding.autocompleteteloupdate.setText(othersDataModel.getTelo());
        binding.parinamoupdate.setText(othersDataModel.getParinamo());
        binding.daroupdate.setText(othersDataModel.getDaro());
        binding.kaifiyatoupdate.setText(othersDataModel.getKaifiyato());
        date();
        autocomplete();
        vat();


        update();
    }

    private void vat() {
        binding.vatbuttonu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!binding.vatu.getText().toString().isEmpty()) {
                    float withvat = Float.parseFloat(binding.vatu.getText().toString());
                    float vat = 0.13f * withvat;

                    float withoutvat = withvat - vat;
                    binding.vattextu.setText("भ्याट रकम => " +vat + "\nभ्याट रहित रकम => " + withoutvat);
                    if (!binding.daroupdate.getText().toString().isEmpty()) {
                        float dar = Float.parseFloat(binding.daroupdate.getText().toString());
                        float darwithoutvat = withoutvat / dar;
                        binding.parinamoupdate.setText("" + darwithoutvat);
                    } else {
                        binding.parinamoupdate.setText("");
                    }
                }
                else {
                    binding.vattextu.setText("");
                }
            }
        });
    }
    private void date() {
        binding.mitioupdate.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.length() == 4) {
                    binding.mitioupdate.append("-");
                }
                if (s.length() == 7) {
                    binding.mitioupdate.append("-");
                }
                if (s.length() == 10) {
                    binding.mitioupdate.append("/");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        });
    }

    private boolean isConnected(updateothers uother) {
        ConnectivityManager connectivityManager = (ConnectivityManager) uother.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if ((wifiConn != null && wifiConn.isConnected()) || (mobileConn != null && mobileConn.isConnected())) {
            return true;
        } else {
            return false;
        }
    }

    private void showCustomDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(updateothers.this);
        builder.setMessage("No Internet Connection! Please Connect to the Internet").setCancelable(false).setPositiveButton("connect", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(getApplicationContext(), others.class));
                finish();
            }
        }).show();
    }

    private void update() {

        binding.updateo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isConnected(updateothers.this)) {
                    showCustomDialog();
                }
                else if (!validateMiti() | !validateParinam() | !validateDar() | !validateKarobar() | !validateTel()) {
                    return;
                }

                else {

                    builder = new AlertDialog.Builder(updateothers.this);
                    builder.setTitle("Alert").setMessage("Are you sure to update ?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            int radioid = binding.karobarupdate.getCheckedRadioButtonId();
                            String karobaro = "";
                            if (radioid != -1) {
                                radioButton = findViewById(radioid);
                                karobaro = radioButton.getText().toString();
                            }

                            String mitioupdate = binding.mitioupdate.getText().toString();
                            String telo = binding.autocompleteteloupdate.getText().toString();
                            String parinamoupdate = binding.parinamoupdate.getText().toString();
                            String daroupdate = binding.daroupdate.getText().toString();
                            String kaifiyatoupdate = binding.kaifiyatoupdate.getText().toString();

                            othersDataModel.setMitio(mitioupdate);
                            othersDataModel.setTelo(telo);
                            othersDataModel.setKarobaro(karobaro);
                            othersDataModel.setParinamo(parinamoupdate);
                            othersDataModel.setDaro(daroupdate);
                            othersDataModel.setKaifiyato(kaifiyatoupdate);
                            if (binding.karobarupdate.getCheckedRadioButtonId() == -1 || mitioupdate.isEmpty() || parinamoupdate.isEmpty() || daroupdate.isEmpty()) {
                                Toast.makeText(updateothers.this, "fill the unfilled", Toast.LENGTH_SHORT).show();

                            } else {
                                updatedata(mitioupdate, parinamoupdate, daroupdate, kaifiyatoupdate);
                            }
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
        String tel = binding.autocompleteteloupdate.getText().toString();
        if (tel.isEmpty()) {
            binding.autocompleteteloupdate.setError("कृपया कुनै एक छनोट गर्नुहोस् ।");
            return false;
        } else {
            binding.autocompleteteloupdate.setError(null);
            return true;
        }
    }

    private boolean validateKarobar() {
        int id = binding.karobarupdate.getCheckedRadioButtonId();
        if (id==-1) {
            binding.radioErrorupdate.setError("कृपया कुनै एक छनोट गर्नुहोस् ।");
            return false;
        } else {
            binding.radioErrorupdate.setError(null);
            return true;
        }

    }

    private boolean validateDar() {
        String dar = binding.daroupdate.getText().toString();
        if (dar.isEmpty()) {
            binding.daroupdate.setError("कृपया दर प्रदान गर्नुहोस् ।");
            return false;
        }
        if (Float.parseFloat(dar) == 0) {
            binding.daroupdate.setError("दर शून्य हुनसक्दैन ।");
            return false;
        } else {
            binding.daroupdate.setError(null);
            return true;
        }
    }

    private boolean validateParinam() {
        String parinam = binding.parinamoupdate.getText().toString();
        if (parinam.isEmpty()) {
            binding.parinamoupdate.setError("कृपया परिमाण प्रदान गर्नुहोस् ।");
            return false;
        }
        if (Float.parseFloat(parinam) == 0) {
            binding.parinamoupdate.setError("परिमाण शून्य हुनसक्दैन ।");
            return false;
        } else {
            binding.parinamoupdate.setError(null);
            return true;
        }
    }

    private boolean validateMiti() {
        String miti = binding.mitioupdate.getText().toString();
        if (miti.isEmpty()) {
            binding.mitioupdate.setError("कृपया मिती प्रदान गर्नुहोस् ।");
            return false;
        }
        if (miti.length() != 11) {
            binding.mitioupdate.setError("कृपया सही मिती प्रदान गर्नुहोस् ।");
            return false;
        } else {
            binding.mitioupdate.setError(null);
            return true;
        }
    }
    private void autocomplete() {
        String[] tel = getResources().getStringArray(R.array.tel);
        ArrayAdapter teladapter = new ArrayAdapter(this, R.layout.dropdowncustom, tel);
        binding.autocompleteteloupdate.setAdapter(teladapter);
    }

    private void updatedata(String mitioupdate, String parinamoupdate, String
            daroupdate, String kaifiyatoupdate) {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating.... Please wait");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        String url = "https://script.google.com/macros/s/AKfycbwl1JQ_XFisszYI8louFaRUKYIss6Cx0dJwONrTvcNh3cZGdq9rDe-ug3FBA9XKFyxxmA/exec" + "?action=updateothers&id=" + othersDataModel.getIdo() + "&miti="
                + othersDataModel.getMitio() + "&tel=" + othersDataModel.getTelo() + "&karobar=" + othersDataModel.getKarobaro() + "&parinam=" + othersDataModel.getParinamo() + "&dar=" + othersDataModel.getDaro() + "&kaifiyat=" + othersDataModel.getKaifiyato();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(updateothers.this, "Updated", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                startActivity(new Intent(updateothers.this, others.class));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(updateothers.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);

    }
}