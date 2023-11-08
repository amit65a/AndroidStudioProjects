package com.example.karobarapp.Others;


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
import com.example.karobarapp.MBC.mbc;
import com.example.karobarapp.R;
import com.example.karobarapp.databinding.ActivityOthersBinding;
import com.example.karobarapp.models.MBCDataModel;
import com.example.karobarapp.models.OthersDataModel;
import com.example.karobarapp.progressdiag.LoadingDiag;

import java.text.DecimalFormat;

public class others extends AppCompatActivity {
    ActivityOthersBinding binding;

    OthersDataModel othersDataModel;
    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOthersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        date();
        read();
        autocomplete();
        submit();


        vat();

    }

    private void vat() {
        binding.vatbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!binding.vat.getText().toString().isEmpty()) {
                    float withvat = Float.parseFloat(binding.vat.getText().toString());
                    float vat = 0.13f * withvat;

                    float withoutvat = withvat - vat;
                    binding.vattext.setText("भ्याट रकम => " +vat + "\nभ्याट रहित रकम => " + withoutvat);
                    if (!binding.daro.getText().toString().isEmpty()) {
                        float dar = Float.parseFloat(binding.daro.getText().toString());
                        float darwithoutvat = withoutvat / dar;
                        binding.parinamo.setText("" +darwithoutvat);
                    } else {
                        binding.parinamo.setText("");
                    }
                }
                else {
                    binding.vattext.setText("");
                }
            }
        });
    }

    private void read() {
        binding.readothers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isConnected(others.this)) {
                    Toast.makeText(others.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
//                    showCustomDialog();
                } else {
                    startActivity(new Intent(others.this, readDataOthers.class));
                }

            }
        });
    }

    private void autocomplete() {
        String[] tel = getResources().getStringArray(R.array.tel);
        ArrayAdapter teladapter = new ArrayAdapter(this, R.layout.dropdowncustom, tel);
        binding.autocompletetelo.setAdapter(teladapter);
    }

    private boolean isConnected(others other) {
        ConnectivityManager connectivityManager = (ConnectivityManager) other.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if ((wifiConn != null && wifiConn.isConnected()) || (mobileConn != null && mobileConn.isConnected())) {
            return true;
        } else {
            return false;
        }
    }

    private void showCustomDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(others.this);
        builder.setMessage("No Internet Connection! Please Connect to the Internet").setCancelable(false).setPositiveButton("connect", new DialogInterface.OnClickListener() {
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

    private void submit() {
        binding.submito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isConnected(others.this)) {
                    showCustomDialog();
                } else if (!validateMiti() | !validateParinam() | !validateDar() | !validateKarobar() | !validateTel()) {
                    return;
                } else {
                    String mitio = binding.mitio.getText().toString();
                    int id = binding.karobar.getCheckedRadioButtonId();
                    String karobaro = "";
                    if (id != -1) {
                        radioButton = findViewById(id);
                        karobaro = radioButton.getText().toString();
                    }
                    String telo = binding.autocompletetelo.getText().toString();
                    String parinamo = binding.parinamo.getText().toString();
                    String daro = binding.daro.getText().toString();
                    String kaifiyato = binding.kaifiyato.getText().toString();


                    if (binding.karobar.getCheckedRadioButtonId() == -1 || mitio.isEmpty() || parinamo.isEmpty() || daro.isEmpty()) {
                        Toast.makeText(others.this, "fill the unfilled", Toast.LENGTH_SHORT).show();
                    } else {
                        othersDataModel = new OthersDataModel();
                        othersDataModel.setMitio(mitio);
                        othersDataModel.setKarobaro(karobaro);
                        othersDataModel.setTelo(telo);
                        othersDataModel.setParinamo(parinamo);
                        othersDataModel.setDaro(daro);
                        othersDataModel.setKaifiyato(kaifiyato);
                        saveDataapi(mitio, karobaro, telo, parinamo, daro, kaifiyato);

                    }
                }

            }
        });
    }

    private boolean validateTel() {
        String tel = binding.autocompletetelo.getText().toString();
        if (tel.isEmpty()) {
            binding.autocompletetelo.setError("कृपया कुनै एक छनोट गर्नुहोस् ।");
            return false;
        } else {
            binding.autocompletetelo.setError(null);
            return true;
        }
    }

    private boolean validateKarobar() {
        int id = binding.karobar.getCheckedRadioButtonId();
        if (id == -1) {
            binding.radioError.setError("कृपया कुनै एक छनोट गर्नुहोस् ।");
            return false;
        } else {
            binding.radioError.setError(null);
            return true;
        }

    }

    private boolean validateDar() {
        String dar = binding.daro.getText().toString();
        if (dar.isEmpty()) {
            binding.daro.setError("कृपया दर प्रदान गर्नुहोस् ।");
            return false;
        }
        if (Float.parseFloat(dar) == 0) {
            binding.daro.setError("दर शून्य हुनसक्दैन ।");
            return false;
        } else {
            binding.daro.setError(null);
            return true;
        }
    }

    private boolean validateParinam() {
        String parinam = binding.parinamo.getText().toString();
        if (parinam.isEmpty()) {
            binding.parinamo.setError("कृपया परिमाण प्रदान गर्नुहोस् ।");
            return false;
        }
        if (Float.parseFloat(parinam) == 0) {
            binding.parinamo.setError("परिमाण शून्य हुनसक्दैन ।");
            return false;
        } else {
            binding.parinamo.setError(null);
            return true;
        }
    }

    private boolean validateMiti() {
        String miti = binding.mitio.getText().toString();
        if (miti.isEmpty()) {
            binding.mitio.setError("कृपया सही मिती प्रदान गर्नुहोस् ।");
            return false;
        }
        if (miti.length() != 11) {
            binding.mitio.setError("कृपया मिती प्रदान गर्नुहोस् ।");
            return false;
        } else {
            binding.mitio.setError(null);
            return true;
        }
    }

    private void date() {
        binding.mitio.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.length() == 4) {
                    binding.mitio.append("-");
                }
                if (s.length() == 7) {
                    binding.mitio.append("-");
                }
                if (s.length() == 10) {
                    binding.mitio.append("/");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        });
    }

    private void saveDataapi(String mitio, String karobaro, String telo, String parinamo, String daro, String kaifiyato) {
        ProgressDialog progressDialog = new ProgressDialog(others.this);
        progressDialog.setMessage("Submitting.... Please wait");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        String url = "https://script.google.com/macros/s/AKfycbwl1JQ_XFisszYI8louFaRUKYIss6Cx0dJwONrTvcNh3cZGdq9rDe-ug3FBA9XKFyxxmA/exec" + "?action=createothers&miti="
                + mitio + "&tel=" + telo + "&karobar=" + karobaro + "&parinam=" + parinamo + "&dar=" + daro + "&kaifiyat=" + kaifiyato;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(others.this, "Submitted", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                binding.autocompletetelo.setText("");
                binding.mitio.setText("");
                binding.parinamo.setText("");
                binding.daro.setText("");
                binding.kaifiyato.setText("");
                binding.karobar.clearCheck();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(others.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }
}



