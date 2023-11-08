package com.example.imagebuttonimageviewratingbardatepickertimepicker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class Date_Picker_Dialog extends AppCompatActivity {
    TextView tv_datepickerdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker_dialog);
        tv_datepickerdialog=(TextView) findViewById(R.id.tv_datepickerdialog);

        Calendar c=Calendar.getInstance();
        int day=c.get(Calendar.DAY_OF_MONTH);
        int month=c.get(Calendar.MONTH);
        int year=c.get(1);

        tv_datepickerdialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dpd=new DatePickerDialog(Date_Picker_Dialog.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        tv_datepickerdialog.setText(day+"/"+(month+1)+"/"+year);
                    }
                },year,month,day);
                dpd.show();
            }
        });
    }
}