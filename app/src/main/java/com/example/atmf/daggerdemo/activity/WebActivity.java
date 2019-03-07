package com.example.atmf.daggerdemo.activity;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.atmf.daggerdemo.R;
import com.example.atmf.daggerdemo.utils.ProgresDialog;

public class WebActivity extends AppCompatActivity {
    private WebView mwebView;
    private Toolbar toolbar;
    private ProgressBar progressBar;
    private ProgresDialog dialog;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        dialog=new ProgresDialog(this);
        mwebView=findViewById(R.id.web);
        toolbar=findViewById(R.id.web_toolbar);
        progressBar=findViewById(R.id.progress);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getIntent().getStringExtra("name"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mwebView.getSettings().setJavaScriptEnabled(true);
        mwebView.getSettings().setLoadWithOverviewMode(true);
        mwebView.getSettings().setDefaultTextEncodingName("utf-8");
        mwebView.getSettings().setLoadsImagesAutomatically(true);
        mwebView.getSettings().setBuiltInZoomControls(false);
        mwebView.getSettings().setSupportZoom(false);
        mwebView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        mwebView.requestFocusFromTouch();
        mwebView.getSettings().setUseWideViewPort(true);
        mwebView.removeJavascriptInterface("searchBoxJavaBridge_");
        mwebView.removeJavascriptInterface("accessibility");
        mwebView.removeJavascriptInterface("accessibilityTraversal");
        mwebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mwebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        mwebView.getSettings().setAllowFileAccess(true);
        mwebView.getSettings().setAppCacheEnabled(false);
        mwebView.getSettings().setDomStorageEnabled(true);
        mwebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String s) {
                webView.loadUrl(s);
                Log.e("webOverride",s.toString());
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
                dialog.dismiss();
                Log.e("webFinished",url.toString());
            }
        });
        mwebView.setWebChromeClient(new MyWebChromeClient());
        mwebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        mwebView.getSettings().setGeolocationEnabled(true);

        mwebView.loadUrl(getIntent().getStringExtra("url"));
    }

    class MyWebChromeClient extends WebChromeClient{
        @Override
        public void onProgressChanged(WebView view, int newProgress) {

            if(newProgress==100){
                progressBar.setVisibility(View.GONE);
            }else {
                if (progressBar.getVisibility()==View.GONE){
                    progressBar.setVisibility(View.VISIBLE);
                }
                progressBar.setProgress(newProgress);
                dialog.show();
            }
            super.onProgressChanged(view, newProgress);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            if (mwebView.canGoBack()){
                mwebView.goBack();
            }else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
