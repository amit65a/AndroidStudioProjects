package com.example.arogya;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.arogya.Fragments.HomeFragment;
import com.example.arogya.Fragments.ProfileFragment;
import com.example.arogya.Fragments.AppointmentFragment;
import com.example.arogya.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        replaceFragment(new HomeFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case 2131296537:
                    replaceFragment(new HomeFragment());
                    break;
                case 2131296694:
                    replaceFragment(new ProfileFragment());
                    break;
                case 2131296747:
                    replaceFragment(new AppointmentFragment());
                    break;
                default:
                    String wer = String.valueOf(R.id.homes);
                    String wer1 = String.valueOf(R.id.profile);
                    String wer2 = String.valueOf(R.id.setting);
                    binding.view.setText(wer + "   " + wer1 + "   " + wer2);
                    break;

            }

            return true;
        });


    }


    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}