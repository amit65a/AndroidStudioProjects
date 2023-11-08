package com.example.search_viewweb_view.webView;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.search_viewweb_view.R;

public class Web_Viewwith_ProgressBar extends AppCompatActivity {

    WebView web_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        web_view=findViewById(R.id.web_view);

        ProgressDialog processDialog=new ProgressDialog(Web_Viewwith_ProgressBar.this);
        processDialog.setTitle("Page Loading");
        processDialog.setMessage("Page is loading please wait");
        processDialog.setCancelable(false);

        web_view.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                processDialog.show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                processDialog.dismiss();
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                Toast.makeText(Web_Viewwith_ProgressBar.this, "Could not wait", Toast.LENGTH_SHORT).show();
            }

        });


        web_view.loadUrl("https://smartprogramming.in/");
//        web_view.getSettings().setJavaScriptEnabled(false);
//        web_view.getSettings().setSupportMultipleWindows(false);

        web_view.getSettings().setSupportZoom(true);
        web_view.getSettings().setBuiltInZoomControls(true);

    }
}