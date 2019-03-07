package com.example.atmf.daggerdemo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.example.atmf.daggerdemo.R;
import com.example.atmf.daggerdemo.server.TestsService;

public class ScrollActivity extends AppCompatActivity implements View.OnClickListener {

    private RadioButton r1, r2, r3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);
        r1 = findViewById(R.id.rb_course);
        r2 = findViewById(R.id.rb_des);
        r3 = findViewById(R.id.rb_teacher);
        r1.setOnClickListener(this);
        r2.setOnClickListener(this);
        r3.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.rb_course:
                Intent intent=new Intent(this,TestsService.class);
                startService(intent);
                break;
            case R.id.rb_des:
                Intent intent1=new Intent(this,TestsService.class);
                stopService(intent1);
                break;
            case R.id.rb_teacher:
                Intent intent2=new Intent(this,TestsService.class);
                startService(intent2);
                break;
        }

    }
}
