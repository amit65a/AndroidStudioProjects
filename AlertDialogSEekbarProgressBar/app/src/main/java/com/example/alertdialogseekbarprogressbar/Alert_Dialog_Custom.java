package com.example.alertdialogseekbarprogressbar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Alert_Dialog_Custom extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialog_custom);
    }
    public void opencustomalertdialog(View view){
        Dialog dialog=new Dialog(Alert_Dialog_Custom.this);
        dialog.setContentView(R.layout.custom_alert_dialog);

        dialog.setCancelable(false);
        Button cus_diag_button=dialog.findViewById(R.id.cus_diag_button);
        cus_diag_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                Toast.makeText(Alert_Dialog_Custom.this, "Custom Dialog clicked", Toast.LENGTH_SHORT).show();
            }
        });
         dialog.show();

    }
}