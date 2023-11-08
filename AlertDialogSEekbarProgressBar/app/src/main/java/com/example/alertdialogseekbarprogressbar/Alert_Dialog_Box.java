package com.example.alertdialogseekbarprogressbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Alert_Dialog_Box extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialog_box);
    }
    public void openalertdialog(View view){
        AlertDialog.Builder alert=new AlertDialog.Builder(Alert_Dialog_Box.this);
        alert.setIcon(R.drawable.alert);
        alert.setTitle("Close Window");
        alert.setCancelable(false);
        alert.setMessage("Do you want to close window ?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(Alert_Dialog_Box.this, "You clicked yes", Toast.LENGTH_SHORT).show();
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(Alert_Dialog_Box.this, "You Clicked no", Toast.LENGTH_SHORT).show();
            }
        });
        alert.setNeutralButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(Alert_Dialog_Box.this, "cancel", Toast.LENGTH_SHORT).show();
            }
        });
        alert.show();

    }
}