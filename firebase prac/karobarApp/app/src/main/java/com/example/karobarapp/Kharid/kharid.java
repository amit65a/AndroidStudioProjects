package com.example.karobarapp.Kharid;

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
import com.example.karobarapp.MBC.mbc;
import com.example.karobarapp.Others.others;
import com.example.karobarapp.Others.readDataOthers;
import com.example.karobarapp.R;
import com.example.karobarapp.databinding.ActivityKharidBinding;
import com.example.karobarapp.models.KharidDataModel;
import com.example.karobarapp.models.OthersDataModel;
import com.example.karobarapp.progressdiag.LoadingDiag;

public class kharid extends AppCompatActivity {
    ActivityKharidBinding binding;
    KharidDataModel kharidDataModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityKharidBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        autocomplete();
        date();
        submit();
        read();
    }

    private void date() {
        binding.mitik.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.length() == 4) {
                    binding.mitik.append("-");
                }
                if (s.length() == 7) {
                    binding.mitik.append("-");
                }
                if (s.length() == 10) {
                    binding.mitik.append("/");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        });
    }

    private void read() {
        binding.readkharid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isConnected(kharid.this)) {
                    Toast.makeText(kharid.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
//                    showCustomDialog();
                } else {
                    startActivity(new Intent(kharid.this, readDataKharid.class));
                }
            }
        });
    }

    private void autocomplete() {
        String[] tel = getResources().getStringArray(R.array.tel);
        ArrayAdapter teladapter = new ArrayAdapter(this, R.layout.dropdowncustom, tel);
        binding.autocompletetelk.setAdapter(teladapter);
    }

    private boolean isConnected(kharid khrd) {
        ConnectivityManager connectivityManager = (ConnectivityManager) khrd.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if ((wifiConn != null && wifiConn.isConnected()) || (mobileConn != null && mobileConn.isConnected())) {
            return true;
        } else {
            return false;
        }
    }

    private void showCustomDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(kharid.this);
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
        binding.submitkharid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isConnected(kharid.this)) {
                    Toast.makeText(kharid.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                    showCustomDialog();
                } else if (!validateMiti() | !validateParinam() | !validateDar() | !validateBata() | !validateTel()) {
                    return;
                } else {
                    String mitik = binding.mitik.getText().toString();
                    String telk = binding.autocompletetelk.getText().toString();
                    String batak = binding.batak.getText().toString();
                    String parinamk = binding.parinamk.getText().toString();
                    String dark = binding.dark.getText().toString();
                    String kaifiyatk = binding.kaifiyatk.getText().toString();

                    if (mitik.isEmpty() || parinamk.isEmpty() || dark.isEmpty()) {
                        Toast.makeText(kharid.this, "fill the unfilled", Toast.LENGTH_SHORT).show();
                    } else {
                        kharidDataModel = new KharidDataModel();
                        kharidDataModel.setMitik(mitik);
                        kharidDataModel.setTelk(telk);
                        kharidDataModel.setBatak(batak);
                        kharidDataModel.setParinamk(parinamk);
                        kharidDataModel.setDark(dark);
                        kharidDataModel.setKaifiyatk(kaifiyatk);
                        saveDataapi(mitik, telk, batak, parinamk, dark, kaifiyatk);
                    }
                }


            }
        });
    }

    private boolean validateTel() {
        String tel = binding.autocompletetelk.getText().toString();
        if (tel.isEmpty()) {
            binding.autocompletetelk.setError("कृपया कुनै एक छनोट गर्नुहोस् ।");
            return false;
        } else {
            binding.autocompletetelk.setError(null);
            return true;
        }
    }

    private boolean validateBata() {
        String bata = binding.batak.getText().toString();
        if (bata.isEmpty()) {
            binding.batak.setError("कृपया बाट प्रदान गर्नुहोस् ।");
            return false;
        } else {
            binding.batak.setError(null);
            return true;
        }

    }

    private boolean validateDar() {
        String dar = binding.dark.getText().toString();
        if (dar.isEmpty()) {
            binding.dark.setError("कृपया दर प्रदान गर्नुहोस् ।");
            return false;
        }
        if (Float.parseFloat(dar) == 0) {
            binding.dark.setError("दर शून्य हुनसक्दैन ।");
            return false;
        } else {
            binding.dark.setError(null);
            return true;
        }
    }

    private boolean validateParinam() {
        String parinam = binding.parinamk.getText().toString();
        if (parinam.isEmpty()) {
            binding.parinamk.setError("कृपया परिमाण प्रदान गर्नुहोस् ।");
            return false;
        }
        if (Float.parseFloat(parinam) == 0) {
            binding.parinamk.setError("परिमाण शून्य हुनसक्दैन ।");
            return false;
        } else {
            binding.parinamk.setError(null);
            return true;
        }
    }

    private boolean validateMiti() {
        String miti = binding.mitik.getText().toString();
        if (miti.isEmpty()) {
            binding.mitik.setError("कृपया मिती प्रदान गर्नुहोस् ।");
            return false;
        }
        if (miti.length() != 11) {
            binding.mitik.setError("कृपया सही मिती प्रदान गर्नुहोस् ।");
            return false;
        } else {
            binding.mitik.setError(null);
            return true;
        }
    }

    private void saveDataapi(String mitik, String telk, String batak, String parinamk, String dark, String kaifiyatk) {
        ProgressDialog progressDialog = new ProgressDialog(kharid.this);
        progressDialog.setMessage("Submitting.... Please wait");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        String url = "https://script.google.com/macros/s/AKfycbz_VzLMD50TxgA0GmhcgK2iaVSki2m4zr7MHYHxGPirAHDtBzs8UNo8nIfBOiELNdQazw/exec" + "?action=createkharid&miti="
                + mitik + "&tel=" + telk + "&bata=" + batak + "&parinam=" + parinamk + "&dar=" + dark + "&kaifiyat=" + kaifiyatk;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(kharid.this, "Submitted", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                binding.batak.setText("");
                binding.autocompletetelk.setText("");
                binding.mitik.setText("");
                binding.parinamk.setText("");
                binding.dark.setText("");
                binding.kaifiyatk.setText("");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(kharid.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);


    }
}