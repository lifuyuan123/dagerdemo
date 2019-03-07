package com.example.atmf.daggerdemo.activity;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.atmf.daggerdemo.GlideApp;
import com.example.atmf.daggerdemo.R;
import com.example.atmf.daggerdemo.entry.TitleImageBannerEntry;
import com.example.atmf.daggerdemo.view.ScrollPaintingPageTransformer;
import com.kelin.banner.view.BannerView;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class MZBannerActivity extends AppCompatActivity {
    private MZBannerView mMZBanner;
    private List<String> strings=new ArrayList<>();
    private int nowPosition=0;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mzbanner2);
        mMZBanner = findViewById(R.id.banner);
        strings.add("https://ss1.baidu.com/-4o3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=8dca0d22dd43ad4bb92e40c0b2005a89/03087bf40ad162d9a62a929b1ddfa9ec8b13cd75.jpg");
        strings.add("https://ss0.baidu.com/94o3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=a7ee4b6ab88f8c54fcd3c32f0a282dee/c9fcc3cec3fdfc039afa6e3cd83f8794a5c226b7.jpg");
        strings.add("https://ss2.baidu.com/-vo3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=a818a24c9525bc31345d07986edd8de7/8694a4c27d1ed21b8b195967a16eddc450da3f5b.jpg");
        strings.add("https://ss1.baidu.com/9vo3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=27b13845316d55fbdac670265d234f40/96dda144ad345982b391b10900f431adcbef8415.jpg");
        //这一步必须再setPages之前调用，否则传入的监听是空不会起作用
        mMZBanner.setBannerPageClickListener(new MZBannerView.BannerPageClickListener() {
            @Override
            public void onPageClick(View view, int position) {
                Toast.makeText(MZBannerActivity.this, "position"+position ,Toast.LENGTH_SHORT).show();
            }
        });
        mMZBanner.setPages(strings, new MZHolderCreator<BannerViewHolder>() {
            @Override
            public BannerViewHolder createViewHolder() {
                return new BannerViewHolder();
            }
        },0.85f);
//        mMZBanner.setIndicatorVisible(false);//指示器隐藏

        BannerView bannerView = findViewById(R.id.vp_view_pager);
        //设置自定义翻页动画改变器，也可以不设置。如果没有设置，则使用ViewPager默认的翻页动画。
        bannerView.setPageTransformer(true, new ScrollPaintingPageTransformer());
        //getData()方法是从网络上获取数据。这里只是伪代码。
        List<TitleImageBannerEntry> bannerEntries = getData();
        //设置数据源并开始轮播。如果不希望启动轮播则调用两个参数的方法：bannerView.setEntries(bannerEntries, false); 你也可以通过bannerView.start();的方式启动轮播。
        bannerView.setEntries(bannerEntries);
        bannerView.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMZBanner.pause();//暂停轮播
    }

    @Override
    public void onResume() {
        super.onResume();
        mMZBanner.start();//开始轮播
    }

    public List<TitleImageBannerEntry> getData() {
        List<TitleImageBannerEntry>list=new ArrayList<>();
        list.add(new TitleImageBannerEntry("title","title2",0,"https://ss1.baidu.com/-4o3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=8dca0d22dd43ad4bb92e40c0b2005a89/03087bf40ad162d9a62a929b1ddfa9ec8b13cd75.jpg"));
        list.add(new TitleImageBannerEntry("title2","title22",0,"https://ss0.baidu.com/94o3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=a7ee4b6ab88f8c54fcd3c32f0a282dee/c9fcc3cec3fdfc039afa6e3cd83f8794a5c226b7.jpg"));
        list.add(new TitleImageBannerEntry("title3","title23",0,"https://ss2.baidu.com/-vo3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=a818a24c9525bc31345d07986edd8de7/8694a4c27d1ed21b8b195967a16eddc450da3f5b.jpg"));
        list.add(new TitleImageBannerEntry("title4","title24",0,"https://ss1.baidu.com/9vo3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=27b13845316d55fbdac670265d234f40/96dda144ad345982b391b10900f431adcbef8415.jpg"));
        return list;
    }

    public static class BannerViewHolder implements MZViewHolder<String> {
        private ImageView mImageView;
        @Override
        public View createView(Context context) {
            // 返回页面布局
            View view = LayoutInflater.from(context).inflate(R.layout.banner_item,null);
            mImageView =view.findViewById(R.id.banner_image);
            return view;
        }

        @Override
        public void onBind(Context context, int position, String data) {
            // 数据绑定
            GlideApp.with(context)
                    .load(data)
                    .error(R.mipmap.ic_launcher)
                    .into(mImageView);
        }
    }
}
