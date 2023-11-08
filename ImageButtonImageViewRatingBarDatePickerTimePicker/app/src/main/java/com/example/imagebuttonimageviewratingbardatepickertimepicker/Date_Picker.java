package com.example.imagebuttonimageviewratingbardatepickertimepicker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Date_Picker extends AppCompatActivity {
    DatePicker date_picker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker);
        date_picker=(DatePicker) findViewById(R.id.date_picker);
    }
    public void getdaymonthyear(View view){
//        int day=date_picker.getDayOfMonth();
//        int month=date_picker.getMonth();
//        int year=date_picker.getYear();

//For getting current date and time we use Calendar

        Calendar c=Calendar.getInstance();
        int day=c.get(Calendar.DAY_OF_MONTH);
        int month=c.get(Calendar.MONTH);
        int year=c.get(Calendar.YEAR);
        Toast.makeText(this, "DD/MM/YYYY = "+day+"/"+(month+1)+"/"+year, Toast.LENGTH_SHORT).show();
    }
}