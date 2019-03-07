package com.example.atmf.daggerdemo.layoutMannager;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;

public class TrillLinnerlayoutMannerger extends LinearLayoutManager {

    private PagerSnapHelper pagerSnapHelper;//一次只能滑动一页的帮助类
    private RecyclerView mrv;

    public TrillLinnerlayoutMannerger(Context context) {
        super(context);
        pagerSnapHelper=new PagerSnapHelper();
    }


    @Override
    public void onAttachedToWindow(RecyclerView view) {
        super.onAttachedToWindow(view);
        pagerSnapHelper.attachToRecyclerView(view);
        mrv=view;
    }
}
