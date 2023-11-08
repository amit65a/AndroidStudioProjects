package com.example.progressdialog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

public class ProgressDiag_Horizontal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_diag_horizontal);
    }

    public void horizontalprogressdiag(View view){
        ProgressDialog progressDialog=new ProgressDialog(ProgressDiag_Horizontal.this);
        progressDialog.setTitle("New Title");
        progressDialog.setMessage("Progress......");
        progressDialog.setIcon(R.drawable.home);
        progressDialog.setMax(200);
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();

        Handler handler= new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                progressDialog.incrementProgressBy(1);
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (progressDialog.getProgress() <= progressDialog.getMax()) {
                        Thread.sleep(100);
                        handler.sendMessage(handler.obtainMessage());
                        if (progressDialog.getProgress()==progressDialog.getMax()){
                            progressDialog.dismiss();
                        }
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
}