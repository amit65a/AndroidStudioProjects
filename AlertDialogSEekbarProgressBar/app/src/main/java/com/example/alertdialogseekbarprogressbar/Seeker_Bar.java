package com.example.alertdialogseekbarprogressbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class Seeker_Bar extends AppCompatActivity {

    SeekBar seek_bar;
    TextView tv_sb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seeker_bar);

        seek_bar=findViewById(R.id.seek_bar);
        tv_sb=findViewById(R.id.tv_sb);

        seek_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress_value=0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int new_progress_value, boolean b) {
                progress_value=new_progress_value;
                tv_sb.setText("Progress value = "+progress_value);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
//                tv_sb.setText("Progress value = "+progress_value);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
//                tv_sb.setText("Progress value = "+progress_value);

            }
        });

    }
}