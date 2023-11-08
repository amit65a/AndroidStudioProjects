package com.example.progressdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;

public class Progress_Dialog extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_dialog);
    }
    public void openprogressdiag(View view){
        ProgressDialog progressdialog=new ProgressDialog(Progress_Dialog.this);
        progressdialog.setTitle("Progress Dialog Title");
        progressdialog.setMessage("This is a simple progress dialog box");
        progressdialog.setIcon(R.drawable.home);
        progressdialog.setCancelable(false);
        progressdialog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    progressdialog.dismiss();
                }
                catch (Exception e){
                    e.printStackTrace();
                }

            }
        }).start();

//                  OR
//
//        Thread thread=new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(5000);
//                    progressdialog.dismiss();
//                }
//                catch (Exception e){
//                    e.printStackTrace();
//                }
//
//            }
//        });
//        thread.start();
    }
}