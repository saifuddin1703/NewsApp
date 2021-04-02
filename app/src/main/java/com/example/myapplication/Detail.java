package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

public class Detail extends AppCompatActivity {
WebView webView;
String url,title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent i= getIntent();
         url = i.getStringExtra("url");
         title= i.getStringExtra("title");
//       WebView mwebView= new WebView(this);
        webView= findViewById(R.id.WebView);
//
//        setContentView(mwebView);
//        WebView myWebView = new WebView(getApplicationContext());
//        setContentView(myWebView);
        webView.loadUrl(url);
    }

    public void share(View view) {
        Intent shareintent = new Intent(Intent.ACTION_SEND);
        shareintent.setType("text/plain");
        shareintent.putExtra(Intent.EXTRA_TEXT,title+url);
        Intent chooser = Intent.createChooser(shareintent,"SEnd Using....");
        startActivityForResult(chooser,121);
    }
}