package com.example.arogya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.example.arogya.Login_Registration.Login;
import com.example.arogya.Login_Registration.Registration;
import com.example.arogya.databinding.ActivityAppointmentBinding;

public class Appointment extends AppCompatActivity {

    ActivityAppointmentBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAppointmentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }
}