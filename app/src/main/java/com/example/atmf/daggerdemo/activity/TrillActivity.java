package com.example.atmf.daggerdemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.atmf.daggerdemo.R;
import com.example.atmf.daggerdemo.adapter.TrillAdapter;
import com.example.atmf.daggerdemo.entry.ActivityBean;
import com.example.atmf.daggerdemo.layoutMannager.TrillLinnerlayoutMannerger;

import java.util.ArrayList;
import java.util.List;

public class TrillActivity extends AppCompatActivity {

    private TrillAdapter adapter;
    private TrillLinnerlayoutMannerger manager;
    private List<ActivityBean> list=new ArrayList<>();
    private RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trill);
        initData();
        init();
    }

    private void initData() {
        for (int i = 0; i < 10; i++) {
            list.add(new ActivityBean("title"+i,""));
        }
    }

    private void init() {
        rv=findViewById(R.id.trill_rv);
        manager=new TrillLinnerlayoutMannerger(this);
        adapter=new TrillAdapter(list,this);
        rv.setLayoutManager(manager);
        rv.setHasFixedSize(true);
        rv.setAdapter(adapter);

    }
}
