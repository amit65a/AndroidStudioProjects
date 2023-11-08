package com.example.arogya.VideoFeature;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.example.arogya.Login_Registration.Login;
import com.example.arogya.Login_Registration.Registration;
import com.example.arogya.R;
import com.example.arogya.databinding.ActivityVideoProfileBinding;
import com.zegocloud.uikit.service.defines.ZegoUIKitUser;

import java.util.Collections;

public class VideoProfile extends AppCompatActivity {

    ActivityVideoProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityVideoProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VideoProfile.this, VideoMain.class));
            }
        });

        binding.userName.setText(getIntent().getStringExtra("caller"));
        binding.usertext.addTextChangedListener(new TextWatcher(){

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                startvideocall(binding.usertext.getText().toString().trim());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    public void startvideocall(String targetUserID){

        binding.callbtn.setIsVideoCall(true);
        binding.callbtn.setResourceID("zego_uikit_call");
        binding.callbtn.setInvitees(Collections.singletonList(new ZegoUIKitUser(targetUserID,targetUserID)));
    }
}