package com.example.atmf.daggerdemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.atmf.daggerdemo.R;
import com.example.atmf.daggerdemo.entry.EntryA;
import com.example.atmf.daggerdemo.entry.EntryB;
import com.example.atmf.daggerdemo.entry.EntryD;
import com.example.atmf.daggerdemo.interfaces.DaggerFiveActivityComponent;
import com.example.atmf.daggerdemo.interfaces.DaggerFourActivityComponent;
import com.example.atmf.daggerdemo.interfaces.FourActivityComponent;
import com.example.atmf.daggerdemo.moudle.EntryABMoudle;


import javax.inject.Inject;

public class FiveActivity extends AppCompatActivity {

    @Inject
    EntryD entryD;
    @Inject
    EntryB entryB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five);
        //component依赖component
        FourActivityComponent fourActivityComponent= DaggerFourActivityComponent.builder().entryABMoudle(new EntryABMoudle(new EntryA())).build();
        DaggerFiveActivityComponent.builder().fourActivityComponent(fourActivityComponent).build().inject(this);

        if (entryD==null){
            ((TextView)findViewById(R.id.tv5_hello)).setText("复杂moudle失败");
        }else {
            ((TextView)findViewById(R.id.tv5_hello)).setText("复杂moudle成功");
        }
    }
}
