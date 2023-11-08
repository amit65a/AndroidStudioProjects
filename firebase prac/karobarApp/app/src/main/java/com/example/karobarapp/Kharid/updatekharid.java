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
import com.example.karobarapp.MBC.updatembc;
import com.example.karobarapp.Others.updateothers;
import com.example.karobarapp.R;
import com.example.karobarapp.databinding.ActivityUpdatekharidBinding;
import com.example.karobarapp.models.KharidDataModel;
import com.example.karobarapp.models.OthersDataModel;

public class updatekharid extends AppCompatActivity {
    ActivityUpdatekharidBinding binding;

    KharidDataModel kharidDataModel;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdatekharidBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        kharidDataModel = (KharidDataModel) getIntent().getSerializableExtra("modelkharid");

        binding.mitikupdate.setText(kharidDataModel.getMitik());
        binding.autocompletetelkupdate.setText(kharidDataModel.getTelk());
        binding.batakupdate.setText(kharidDataModel.getBatak());
        binding.parinamkupdate.setText(kharidDataModel.getParinamk());
        binding.darkupdate.setText(kharidDataModel.getDark());
        binding.kaifiyatkupdate.setText(kharidDataModel.getKaifiyatk());
        date();

        autocomplete();
        update();
    }

    private void date() {
        binding.mitikupdate.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.length() == 4) {
                    binding.mitikupdate.append("-");
                }
                if (s.length() == 7) {
                    binding.mitikupdate.append("-");
                }
                if (s.length() == 10) {
                    binding.mitikupdate.append("/");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        });
    }

    private boolean isConnected(updatekharid ukharid) {
        ConnectivityManager connectivityManager = (ConnectivityManager) ukharid.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if ((wifiConn != null && wifiConn.isConnected()) || (mobileConn != null && mobileConn.isConnected())) {
            return true;
        } else {
            return false;
        }
    }

    private void showCustomDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(updatekharid.this);
        builder.setMessage("No Internet Connection! Please Connect to the Internet").setCancelable(false).setPositiveButton("connect", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(getApplicationContext(), kharid.class));
                finish();
            }
        }).show();
    }

    private void update() {
        binding.updatekharid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isConnected(updatekharid.this)) {
                    showCustomDialog();
                } else if (!validateMiti() | !validateParinam() | !validateDar() | !validateBata() | !validateTel()) {
                    return;
                } else {

                    builder = new AlertDialog.Builder(updatekharid.this);
                    builder.setTitle("Alert").setMessage("Are you sure to update ?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String mitik = binding.mitikupdate.getText().toString();
                            String telk = binding.autocompletetelkupdate.getText().toString();
                            String batak = binding.batakupdate.getText().toString();
                            String parinamk = binding.parinamkupdate.getText().toString();
                            String dark = binding.darkupdate.getText().toString();
                            String kaifiyat = binding.kaifiyatkupdate.getText().toString();

                            kharidDataModel.setMitik(mitik);
                            kharidDataModel.setTelk(telk);
                            kharidDataModel.setBatak(batak);
                            kharidDataModel.setParinamk(parinamk);
                            kharidDataModel.setDark(dark);
                            kharidDataModel.setKaifiyatk(kaifiyat);


                            updatedata(mitik, telk, batak, parinamk, dark, kaifiyat);
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
        String tel = binding.autocompletetelkupdate.getText().toString();
        if (tel.isEmpty()) {
            binding.autocompletetelkupdate.setError("कृपया कुनै एक छनोट गर्नुहोस् ।");
            return false;
        } else {
            binding.autocompletetelkupdate.setError(null);
            return true;
        }
    }

    private boolean validateBata() {
        String bata = binding.batakupdate.getText().toString();
        if (bata.isEmpty()) {
            binding.batakupdate.setError("कृपया बाट प्रदान गर्नुहोस् ।");
            return false;
        } else {
            binding.batakupdate.setError(null);
            return true;
        }

    }

    private boolean validateDar() {
        String dar = binding.darkupdate.getText().toString();
        if (dar.isEmpty()) {
            binding.darkupdate.setError("कृपया दर प्रदान गर्नुहोस् ।");
            return false;
        }
        if (Float.parseFloat(dar) == 0) {
            binding.darkupdate.setError("दर शून्य हुनसक्दैन ।");
            return false;
        } else {
            binding.darkupdate.setError(null);
            return true;
        }
    }

    private boolean validateParinam() {
        String parinam = binding.parinamkupdate.getText().toString();
        if (parinam.isEmpty()) {
            binding.parinamkupdate.setError("कृपया परिमाण प्रदान गर्नुहोस् ।");
            return false;
        }
        if (Float.parseFloat(parinam) == 0) {
            binding.parinamkupdate.setError("परिमाण शून्य हुनसक्दैन ।");
            return false;
        } else {
            binding.parinamkupdate.setError(null);
            return true;
        }
    }

    private boolean validateMiti() {
        String miti = binding.mitikupdate.getText().toString();
        if (miti.isEmpty()) {
            binding.mitikupdate.setError("कृपया मिती प्रदान गर्नुहोस् ।");
            return false;
        }
        if (miti.length() != 11) {
            binding.mitikupdate.setError("कृपया सही मिती प्रदान गर्नुहोस् ।");
            return false;
        } else {
            binding.mitikupdate.setError(null);
            return true;
        }
    }

    private void updatedata(String mitik, String telk, String batak, String parinamk, String dark, String kaifiyat) {

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating.... Please wait");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        String url = "https://script.google.com/macros/s/AKfycbz_VzLMD50TxgA0GmhcgK2iaVSki2m4zr7MHYHxGPirAHDtBzs8UNo8nIfBOiELNdQazw/exec" + "?action=updatekharid&id=" + kharidDataModel.getIdk() + "&miti="
                + kharidDataModel.getMitik() + "&tel=" + kharidDataModel.getTelk() + "&bata=" + kharidDataModel.getBatak() + "&parinam=" + kharidDataModel.getParinamk() + "&dar=" + kharidDataModel.getDark() + "&kaifiyat=" + kharidDataModel.getKaifiyatk();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();
                Toast.makeText(updatekharid.this, response, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(updatekharid.this, kharid.class));


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(updatekharid.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);

    }

    private void autocomplete() {
        String[] tel = getResources().getStringArray(R.array.tel);
        ArrayAdapter teladapter = new ArrayAdapter(this, R.layout.dropdowncustom, tel);
        binding.autocompletetelkupdate.setAdapter(teladapter);
    }
}