package com.example.karobarapp.progressdiag;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

import com.example.karobarapp.R;

public class LoadingDiag {
    private Activity activity;
    private AlertDialog dialog;
    public LoadingDiag(Activity myactivity){
        activity=myactivity;
    }
    public void startLoadingDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(activity);

        LayoutInflater inflater=activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.custom_dialog,null));
        builder.setCancelable(false);
        dialog=builder.create();
        dialog.show();
    }
    public void dismissDialog(){
        dialog.dismiss();
    }
}
