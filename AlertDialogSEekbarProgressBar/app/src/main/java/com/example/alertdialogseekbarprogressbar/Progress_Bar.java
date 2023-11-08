package com.example.alertdialogseekbarprogressbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class Progress_Bar extends AppCompatActivity {

    Button Btn_progress;
    ProgressBar progress_bar;
    int progress_value=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);

        progress_bar=findViewById(R.id.progress_bar);
        Btn_progress=findViewById(R.id.Btn_progress);

    }
    public void startprogressbar(View view){
        Btn_progress.setVisibility(View.GONE);
        setProgressBarValue(progress_value);
    }
    void setProgressBarValue(int i){
        Thread thread =new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                progress_bar.setProgress(i);
                setProgressBarValue(i+1);
            }

        });
        thread.start();


    }
}