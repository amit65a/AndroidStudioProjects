package com.example.imagebuttonimageviewratingbardatepickertimepicker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class Time_Picker_Dialog extends AppCompatActivity {
    TextView tv_timedialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_picker_dialog);

        tv_timedialog=findViewById(R.id.tv_timedialog);
        Calendar c=Calendar.getInstance();
        int hour=c.get(Calendar.HOUR);
        int min=c.get(12);
        tv_timedialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog tmd=new TimePickerDialog(Time_Picker_Dialog.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int min) {
                        tv_timedialog.setText(hour+":"+min);
                    }

                },hour,min,true);
                tmd.show();
            }
        });
    }

}