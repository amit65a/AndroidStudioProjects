package com.example.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.model.databinding.ActivityMainBinding;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    TextToSpeech tts;
    private static final int REQUEST_AUDIO_PERMISSION_CODE=101;
    MediaRecorder mediaRecorder;
    boolean isRecording =false;
    String path=null;

    int second,dummysecond,playablesecond=0;

    Handler handler;
    ExecutorService executorService= Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        autocomplete();
        record();

        binding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                speakNow();
//                binding.speech.setText(binding.speechText.getText().toString());
                tts=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status==TextToSpeech.SUCCESS){
                            tts.setLanguage(Locale.US);
                            tts.setSpeechRate(1.0f);
                            tts.speak(binding.speech.getText().toString(),TextToSpeech.QUEUE_ADD,null);
                            binding.speech.setText("");
                        }
                    }
                });
            }
        });
    }

    private void speakNow() {
        Intent intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Start Speaking");
        startActivityForResult(intent,111);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==111&&resultCode==RESULT_OK){
            binding.speechText.setText(data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS).get(0));
        }
    }

    private void record() {
        binding.record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkRecordingPermission()){
                    if (!isRecording){
                        isRecording=true;
                        executorService.execute(new Runnable() {
                            @Override
                            public void run() {
                                mediaRecorder=new MediaRecorder();
                                mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                                mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);

                                mediaRecorder.setOutputFile(getRecordingFle());
                                path=getRecordingFle();
                                mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                                try {
                                    mediaRecorder.prepare();
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                                mediaRecorder.start();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        second=0;
                                        playablesecond=0;
                                        dummysecond=0;
                                        binding.path.setText(getRecordingFle());
                                        binding.record.setImageDrawable(ContextCompat.getDrawable(MainActivity.this,R.drawable.micactive));
                                        runTimer();


                                    }
                                });
                            }
                        });
                    }
                    else {
                        executorService.execute(new Runnable() {
                            @Override
                            public void run() {
                                mediaRecorder.stop();
                                mediaRecorder.release();
                                mediaRecorder=null;
                                playablesecond=second;
                                dummysecond=second;
                                second=0;
                                isRecording=false;

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        handler.removeCallbacksAndMessages(null);
                                        binding.record.setImageDrawable(ContextCompat.getDrawable(MainActivity.this,R.drawable.micinactive));

                                    }
                                });
                            }
                        });
                    }

                }
                else{
                    requestRecodingPermission();
                }
            }
        });
    }

    private void runTimer() {
        handler=new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int min=(second%3600)/60;
                int sec=second%60;
                String time=String.format(Locale.getDefault(),"%02d:%02d",min,sec);
                binding.playTime.setText(time);
                if (isRecording){
                    second++;
                }
                handler.postDelayed(this,1000);
            }
        });
    }

    private String getRecordingFle() {
        ContextWrapper contextWrapper=new ContextWrapper(getApplicationContext());
        File music=contextWrapper.getExternalFilesDir(Environment.DIRECTORY_MUSIC);
        File file=new File(music,"file"+".mp3");
        return file.getPath();
    }

    private void requestRecodingPermission() {
        ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.RECORD_AUDIO},REQUEST_AUDIO_PERMISSION_CODE);
    }

    private boolean checkRecordingPermission() {
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.RECORD_AUDIO)== PackageManager.PERMISSION_DENIED) {
            requestRecodingPermission();
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==REQUEST_AUDIO_PERMISSION_CODE){
            if (grantResults.length>0){
                boolean permissionToRecord=grantResults[0]==PackageManager.PERMISSION_GRANTED;
                if (permissionToRecord){
                    Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void autocomplete() {
        String[] fromlist = getResources().getStringArray(R.array.from);
        ArrayAdapter fromadapter = new ArrayAdapter(this, R.layout.dropdowncustom, fromlist);
        binding.autoCompletefrom.setAdapter(fromadapter);
        String[] tolist = getResources().getStringArray(R.array.to);
        ArrayAdapter toadapter = new ArrayAdapter(this, R.layout.dropdowncustom, tolist);
        binding.autoCompleteto.setAdapter(toadapter);

    }
}