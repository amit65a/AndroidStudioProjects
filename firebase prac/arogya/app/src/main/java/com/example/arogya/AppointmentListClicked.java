package com.example.arogya;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.arogya.Login_Registration.Login;
import com.example.arogya.Login_Registration.Registration;
import com.example.arogya.VideoFeature.VideoMain;
import com.example.arogya.VideoFeature.VideoProfile;
import com.example.arogya.databinding.ActivityAppointmentBinding;
import com.example.arogya.databinding.ActivityAppointmentListClickedBinding;
import com.zegocloud.uikit.prebuilt.call.config.ZegoNotificationConfig;
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationConfig;
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationService;

public class AppointmentListClicked extends AppCompatActivity {
    ActivityAppointmentListClickedBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAppointmentListClickedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.StartCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AppointmentListClicked.this, VideoMain.class));


//                startmyservice(binding.drid.getText().toString());
//                Intent intent = new Intent(getApplicationContext(), VideoProfile.class);
//                intent.putExtra("caller", binding.drid.getText().toString().trim());
//                startActivity(intent);
            }
        });
    }

//
//    private void startmyservice(String userid) {
//        Application application = getApplication(); // Android's application context
//        long appID = 1639689708;   // yourAppID
//        String appSign = "2fc69707d8b5036732fbd4d2b8a880cd0d73009d259e7851c82dac4a1df54d5e";  // yourAppSign
//        String userID = userid; // yourUserID, userID should only contain numbers, English characters, and '_'.
//        String userName = userid;   // yourUserName
//
//        ZegoUIKitPrebuiltCallInvitationConfig callInvitationConfig = new ZegoUIKitPrebuiltCallInvitationConfig();
//        callInvitationConfig.notifyWhenAppRunningInBackgroundOrQuit = true;
//        ZegoNotificationConfig notificationConfig = new ZegoNotificationConfig();
//        notificationConfig.sound = "zego_uikit_sound_call";
//        notificationConfig.channelID = "CallInvitation";
//        notificationConfig.channelName = "CallInvitation";
//        ZegoUIKitPrebuiltCallInvitationService.init(getApplication(), appID, appSign, userID, userName,callInvitationConfig);
//    }

//    public void onDestroy(){
//        super.onDestroy();
//        ZegoUIKitPrebuiltCallInvitationService.unInit();
//    }




}