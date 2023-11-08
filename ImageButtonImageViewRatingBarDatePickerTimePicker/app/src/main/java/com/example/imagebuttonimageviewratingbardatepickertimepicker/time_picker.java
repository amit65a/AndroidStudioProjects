package com.example.imagebuttonimageviewratingbardatepickertimepicker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

public class time_picker extends AppCompatActivity {
    TimePicker time_picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_picker);

        time_picker=findViewById(R.id.time_picker);
    }
    public void gettime(View view){
        int min=time_picker.getMinute();
        int hour=time_picker.getHour();
        Toast.makeText(this, hour+":"+min, Toast.LENGTH_SHORT).show();
    }
}