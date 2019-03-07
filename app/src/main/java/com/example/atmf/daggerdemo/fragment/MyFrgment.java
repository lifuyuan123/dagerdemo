package com.example.atmf.daggerdemo.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.atmf.daggerdemo.R;
import com.example.atmf.daggerdemo.adapter.FoodAdapter;
import com.example.atmf.daggerdemo.entry.FoodsBean;
import com.paginate.Paginate;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class MyFrgment extends Fragment {
    private String title;
    private FoodAdapter adapter;
    private List<FoodsBean> list=new ArrayList<>();
    private RecyclerView rv;
    private LinearLayoutManager manager;
    private Banner banner;
    private SwipeRefreshLayout refreshLayout;
    private boolean isRefrsh=false;
    private int count=0;
    private Paginate paginate;
    private List<String> images=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return LayoutInflater.from(getActivity()).inflate(R.layout.fragment,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv=view.findViewById(R.id.rv_slide);
        banner=view.findViewById(R.id.banner);
        refreshLayout=view.findViewById(R.id.swipe);
        title=getArguments().getString("title");
        initData();
        adapter=new FoodAdapter(list,getActivity());
        manager=new LinearLayoutManager(getActivity());

//        manager.setSmoothScrollbarEnabled(true);//防止滑动卡顿
//        manager.setAutoMeasureEnabled(true);//防止滑动卡顿
        rv.setLayoutManager(manager);
        new PagerSnapHelper().attachToRecyclerView(rv);//使滑动的item在屏幕中间
//        rv.setHasFixedSize(true);//防止滑动卡顿rv
//        rv.setNestedScrollingEnabled(false);//防止滑动卡顿
        rv.setAdapter(adapter);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                isRefrsh=true;
                count++;
                list.clear();
                initData();
                adapter.notifyDataSetChanged();
                refreshLayout.setRefreshing(false);
            }
        });

        Paginate.Callbacks callbacks=new Paginate.Callbacks() {
            @Override
            public void onLoadMore() {//加载下一页数据
                initData();
                adapter.notifyDataSetChanged();
            }
            @Override
            public boolean isLoading() {//是否正在加载
                return isRefrsh;
            }
            @Override
            public boolean hasLoadedAllItems() {
                return false;
            }
        };

        paginate=Paginate.with(rv,callbacks)
                .setLoadingTriggerThreshold(0)
                .build();
        paginate.setHasMoreDataToLoad(false);//设置有更多数据加载

        //banner相关
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        });
        initImages();
        //设置图片集合
        banner.setImages(images);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    private void initImages() {
        images.add("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=783340072,1312243264&fm=27&gp=0.jpg");
        images.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=132600321,3123081067&fm=27&gp=0.jpg");
        images.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=403792805,263717657&fm=27&gp=0.jpg");
        images.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1759543086,3926457470&fm=27&gp=0.jpg");
    }

    private void initData() {
        for (int i = 0; i < 10; i++) {
            list.add(new FoodsBean(title+count+""+i,"","",""));
        }
        isRefrsh=false;
    }
}
