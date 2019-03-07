package com.example.atmf.daggerdemo.activity;

import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;


import com.example.atmf.daggerdemo.R;

public class MDActivity extends AppCompatActivity {

    private CollapsingToolbarLayout collapsingToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_md);
        collapsingToolbar=findViewById(R.id.toolbar_layout);

        collapsingToolbar.setTitle(getTitle());
        collapsingToolbar.setCollapsedTitleTextColor(Color.BLACK);
        collapsingToolbar.setExpandedTitleColor(Color.BLACK);
    }
}
