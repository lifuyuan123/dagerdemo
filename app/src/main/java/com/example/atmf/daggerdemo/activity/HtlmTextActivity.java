package com.example.atmf.daggerdemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.atmf.daggerdemo.R;

import org.sufficientlysecure.htmltextview.HtmlResImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;

public class HtlmTextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_htlm_text);

        HtmlTextView htmlTextView = (HtmlTextView)findViewById(R.id.html_text);

// loads html from string and displays cat_pic.png from the app's drawable folder
        htmlTextView.setHtml("<h2>Hello wold</h2><ul><li>cats</li><li>dogs</li></ul><img src=\"cat_pic\"/>",
                new HtmlResImageGetter(htmlTextView));
    }
}
