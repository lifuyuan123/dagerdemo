package com.example.atmf.daggerdemo.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.atmf.daggerdemo.R;
import com.example.atmf.daggerdemo.fragment.MyFrgment;
import com.example.atmf.daggerdemo.fragment.MyPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SlideConflictActivity extends AppCompatActivity {

    private TabLayout table;
    private ViewPager pager;
    private FragmentManager manager;
    private List<MyFrgment> fragments=new ArrayList<>();
    private List<String> titles=new ArrayList<>();
    private MyPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_conflict);
        table=findViewById(R.id.tabelayout);
        pager=findViewById(R.id.pager);
        manager=getSupportFragmentManager();
        initData();
        adapter=new MyPagerAdapter(manager,fragments,titles);
        pager.setAdapter(adapter);
        pager.setCurrentItem(0);
        table.setupWithViewPager(pager);
    }

    private void initData() {

        titles.add("阿斯蒂芬");
        titles.add("撒旦发的");
        titles.add("时代的分公司大");
        titles.add("胜多负少");
        titles.add("的故事大纲");

        for (int i = 0; i < titles.size(); i++) {
            MyFrgment fragment=new MyFrgment();
            Bundle bundle=new Bundle();
            bundle.putString("title",titles.get(i));
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }
    }
}
