package com.example.atmf.daggerdemo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import com.example.atmf.daggerdemo.R;
import com.example.atmf.daggerdemo.adapter.ShardAdapter;

public class SharedActivity extends AppCompatActivity {

    RecyclerView rv;
    LinearLayoutManager manager;
    ShardAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared);

        manager=new LinearLayoutManager(this);
        rv=findViewById(R.id.shard_iv);
        adapter=new ShardAdapter(this);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(manager);
        rv.setAdapter(adapter);

//        Slide slideTransition = new Slide();
//        slideTransition.setSlideEdge(Gravity.LEFT);
//        slideTransition.setDuration(500);
//        getWindow().setReenterTransition(slideTransition);
//        getWindow().setExitTransition(slideTransition);
    }


}
