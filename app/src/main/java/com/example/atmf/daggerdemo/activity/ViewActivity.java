package com.example.atmf.daggerdemo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.atmf.daggerdemo.GlideApp;
import com.example.atmf.daggerdemo.adapter.MyAdapter;
import com.example.atmf.daggerdemo.R;
import com.example.atmf.daggerdemo.entry.ActivityBean;
import com.example.atmf.daggerdemo.server.TestsService;

import java.util.ArrayList;
import java.util.List;

public class ViewActivity extends AppCompatActivity {



    private LinearLayoutManager manager=new LinearLayoutManager(this);
    private MyAdapter adapter;
    private RecyclerView rv;
    private List<ActivityBean> list=new ArrayList<>();
    private ImageView iv;
    private long mPressedTime = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        iv=findViewById(R.id.iv);
        rv=findViewById(R.id.rv);
        adapter=new MyAdapter(list,this);
        initdata();

//        GlideApp.with(this)
//                .load("http://img2.imgtn.bdimg.com/it/u=1645649503,3446658575&fm=27&gp=0.jpg")
//                .circleCrop()
//                .centerCrop()
//                .error(R.mipmap.ic_launcher)
//                .into(iv);




    }

    private void initdata() {
        list.add(new ActivityBean("com.example.atmf.daggerdemo.activity.SpiderActivity","path蜘蛛雷达"));
        list.add(new ActivityBean("com.example.atmf.daggerdemo.activity.SerchActivity","pathMeasure搜索"));
        list.add(new ActivityBean("com.example.atmf.daggerdemo.activity.RxActivity","天下美食"));
        list.add(new ActivityBean("com.example.atmf.daggerdemo.activity.MainActivity","父布局下添加动画属性layoutAnimation以及Html属性效果"));
        list.add(new ActivityBean("com.example.atmf.daggerdemo.activity.CanvasPictureActivity","canvas利用drawBitmap显示局部"));
        list.add(new ActivityBean("com.example.atmf.daggerdemo.activity.SubComponentntActivity","第三方字体和LottieAnimationView动画，EasyPopup以及快排"));
        list.add(new ActivityBean("com.example.atmf.daggerdemo.activity.SlideConflictActivity","滑动冲突"));
        list.add(new ActivityBean("com.example.atmf.daggerdemo.activity.PhysicsActivity","demo"));
        list.add(new ActivityBean("com.example.atmf.daggerdemo.activity.BinderActivity","binder"));
        list.add(new ActivityBean("com.example.atmf.daggerdemo.activity.SharedActivity","共享动画"));
        list.add(new ActivityBean("com.example.atmf.daggerdemo.activity.HtlmTextActivity","htmltext"));
        list.add(new ActivityBean("com.example.atmf.daggerdemo.activity.CalendarActivity","日历"));
        list.add(new ActivityBean("com.example.atmf.daggerdemo.activity.TrillActivity","仿抖音列表"));
        list.add(new ActivityBean("com.example.atmf.daggerdemo.activity.MZBannerActivity","魅族banner"));
        list.add(new ActivityBean("com.example.atmf.daggerdemo.activity.MDActivity","MD Title效果"));
        list.add(new ActivityBean("com.example.atmf.daggerdemo.activity.ShowPicturesActivity","显示图片效果"));
        list.add(new ActivityBean("com.example.atmf.daggerdemo.activity.ScrollActivity","托顶效果"));
        list.add(new ActivityBean("com.example.atmf.daggerdemo.activity.RouandActivity","demo"));
        rv.setLayoutManager(manager);
        rv.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        long mNowTime = System.currentTimeMillis();//获取第一次按键时间
        if ((mNowTime - mPressedTime) > 2000) {//比较两次按键时间差
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            mPressedTime = mNowTime;
        } else {
            Intent intent=new Intent(this,TestsService.class);
            stopService(intent);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        }
    }}
