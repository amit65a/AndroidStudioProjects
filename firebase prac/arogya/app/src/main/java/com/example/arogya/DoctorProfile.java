package com.example.arogya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.arogya.Login_Registration.Login;
import com.example.arogya.Login_Registration.Registration;
import com.example.arogya.databinding.ActivityDoctorProfileBinding;

public class DoctorProfile extends AppCompatActivity {

    ActivityDoctorProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDoctorProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.MakeanAppointmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorProfile.this, Appointment.class));
            }
        });


    }
}