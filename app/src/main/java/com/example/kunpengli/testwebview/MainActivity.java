package com.example.kunpengli.testwebview;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private WebView contentWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contentWebView = (WebView)findViewById(R.id.webview);

        //start js

        contentWebView.getSettings().setJavaScriptEnabled(true);

        //load html from assets

        contentWebView.loadUrl("file:///android_asset/web.html");

        contentWebView.addJavascriptInterface(MainActivity.this,"android");

        //Button call js method with no params

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contentWebView.loadUrl("javascript:javacalljs()");
            }
        });

        //button call js method with params

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contentWebView.loadUrl("javascript:javacalljswith('ssss')");
            }
        });
    }
    @JavascriptInterface
    public void startFunction(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this,"show",Toast.LENGTH_LONG).show();
            }
        });
    }
    @JavascriptInterface
    public void startFunction(final String text){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new AlertDialog.Builder(MainActivity.this).setMessage(text).show();
            }
        });
    }
}
