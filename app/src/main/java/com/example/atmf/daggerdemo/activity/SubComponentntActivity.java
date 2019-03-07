package com.example.atmf.daggerdemo.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.atmf.daggerdemo.R;
import com.example.atmf.daggerdemo.entry.Cloth;
import com.example.atmf.daggerdemo.entry.Food;
import com.example.atmf.daggerdemo.entry.Observer;
import com.example.atmf.daggerdemo.entry.Observerble;
import com.example.atmf.daggerdemo.entry.Person;
import com.example.atmf.daggerdemo.entry.User;
import com.example.atmf.daggerdemo.entry.Weather;
import com.example.atmf.daggerdemo.interfaces.DaggerPackComponet;
import com.example.atmf.daggerdemo.moudle.UserModle;
import com.zyyoona7.popup.EasyPopup;
import com.zyyoona7.popup.XGravity;
import com.zyyoona7.popup.YGravity;


import javax.inject.Inject;

import dagger.Provides;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SubComponentntActivity extends AppCompatActivity {
    @Inject
    User user;
    @Inject
    Cloth mCloth;

    private TextView tv;
    private TextView tv_;
    private TextView tv_point,tv_pl;
    private LinearLayout lin;
    int[] a = new int[]{2,7,4,5,10,1,9,3,8,6};
//    int[] a = new int[]{six,8,three,9,one,10,five,four,7,two};


    private EasyPopup mCirclePop;

    //沉浸式
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_componentnt);

//        if (Build.VERSION.SDK_INT >= 21) {
//            View decorView = getWindow().getDecorView();
//            int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
//            decorView.setSystemUiVisibility(option);
//            getWindow().setNavigationBarColor(Color.TRANSPARENT);
//            getWindow().setStatusBarColor(Color.TRANSPARENT);
//        }
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.hide();

        tv=findViewById(R.id.tv_sub);
        tv_=findViewById(R.id.tv);
        lin=findViewById(R.id.lin_back);
        mCirclePop = EasyPopup.create()
                .setContentView(this, R.layout.content)
//                .setAnimationStyle(R.style.RightPopAnim)
                //是否允许点击PopupWindow之外的地方消失
                .setFocusAndOutsideEnable(true)
                //允许背景变暗
                .setBackgroundDimEnable(true)
                //变暗的透明度(0-one)，0为完全透明
//                .setDimValue(0.4f)
//                //变暗的背景颜色
//                .setDimColor(Color.YELLOW)
//                //指定任意 ViewGroup 背景变暗
//                .setDimView(lin)
                .apply();
        tv_point=mCirclePop.findViewById(R.id.tv_zan);
        tv_pl=mCirclePop.findViewById(R.id.tv_comment);

        tv_point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SubComponentntActivity.this, "点赞", Toast.LENGTH_SHORT).show();
                mCirclePop.dismiss();
            }
        });
        tv_pl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SubComponentntActivity.this, "评论", Toast.LENGTH_SHORT).show();
                mCirclePop.dismiss();
            }
        });

          DaggerPackComponet.builder().build().getUserComponent(new UserModle()).inject(this);

            Toast.makeText(this, ""+user.toString()+"\n"+"cloth:"+mCloth.toString(), Toast.LENGTH_SHORT).show();
            tv.setText(mCloth.toString());
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCirclePop.showAtAnchorView(tv, YGravity.BELOW, XGravity.CENTER, 0, 0);
                }
            });


//            recursiveSort(a,0,a.length-one);
//        sort(a,0,a.length-one);
        mm();
        StringBuffer sb=new StringBuffer();
        for (int i = 0; i < a.length; i++) {
            sb.append(a[i]);

        }

        Person.Builder builder=new Person.Builder();
                   Person person=  builder.age("15")
                             .clazz("计算机")
                             .hight("180")
                             .sex("男")
                             .name("lfy")
                             .build();

        Log.e("日志",sb.toString()+"\n"+person.toString());

        Observerble<Weather> observerble=new Observerble<>();

        Observer<Weather> weather1=new Observer<Weather>() {
            @Override
            public void updata(Weather data) {
             Log.e("w1收到消息：",data.toString());
            }
        };

        Observer<Weather> weather2=new Observer<Weather>() {
            @Override
            public void updata(Weather data) {
                Log.e("w2收到消息：",data.toString());
            }
        };
        observerble.regiset(weather1);
        observerble.regiset(weather2);
        observerble.updata(new Weather("下雨天，留客天"));
        observerble.updata(new Weather("下雨天，不留客"));
        observerble.updata(new Weather("晴天，留客天"));
        observerble.updata(new Weather("晴天，不留客"));





    }

    //绑定的时候调用
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    //冒泡
    private void  mm(){
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length-1 - i; j++) {
                if (a[j] > a[j+1]) {
                    int tem=a[j];
                    a[j]=a[j+1];
                    a[j+1]=tem;
                }
            }
        }
    }

    private int sort(int[] ints,int start,int end){

        //这里选一个基准数
        int p=ints[start];
        //只有当end大于start的时候才进行排序
        while (end>start){

            //当最右边的数大于基准数则继续移动
            while (end>start&&ints[end]>=p){
                end--;//满足条件下标移动
            }

            //运行到这一步说明上一步的while循环已经走完了  此时停留下的end下标所对应的值在小于p
            //互换位置
            if(end>start){
                swap(ints,start,end);
                start++;//替换位置后下表增加
            }

            //当最左边的数小于基准数
            while (end>start&&ints[start]<=p){
                start++;//满足条件下表游动
            }

            //运行到这一步说明上一步的while循环已经走完了  此时停留下的start下标所对应的值在大于p
            if (end>start){
                swap(ints,start,end);
                end--;
            }
        }
        return end;
    }

    //互换位置
    private void swap(int[] ints,int start,int end){
        int middle=ints[start];
        ints[start]=ints[end];
        ints[end]=middle;
    }


    private void recursiveSort(int[] ints,int start,int end){
        if (start>end){
            return;
        }else {
            int a=sort(ints,start,end);
            recursiveSort(ints,start,a-1);
            recursiveSort(ints,a+1,end);

        }
    }


    /**
     * 将数组的某一段元素进行划分，小的在左边，大的在右边
     * @param a
     * @param start
     * @param end
     * @return
     */
//    public int divide(int[] a, int start, int end){
//        //每次都以最右边的元素作为基准值
//        int base = a[end];
//        //start一旦等于end，就说明左右两个指针合并到了同一位置，可以结束此轮循环。
//        while(start < end){
//            while(start < end && a[start] <= base)
//                //从左边开始遍历，如果比基准值小，就继续向右走
//                start++;
//            //上面的while循环结束时，就说明当前的a[start]的值比基准值大，应与基准值进行交换
//            if(start < end){
//                //交换
//                int temp = a[start];
//                a[start] = a[end];
//                a[end] = temp;
//                //交换后，此时的那个被调换的值也同时调到了正确的位置(基准值右边)，因此右边也要同时向前移动一位
//                end--;
//            }
//            while(start < end && a[end] >= base)
//                //从右边开始遍历，如果比基准值大，就继续向左走
//                end--;
//            //上面的while循环结束时，就说明当前的a[end]的值比基准值小，应与基准值进行交换
//            if(start < end){
//                //交换
//                int temp = a[start];
//                a[start] = a[end];
//                a[end] = temp;
//                //交换后，此时的那个被调换的值也同时调到了正确的位置(基准值左边)，因此左边也要同时向后移动一位
//                start++;
//            }
//
//        }
//        //这里返回start或者end皆可，此时的start和end都为基准值所在的位置
//        return end;
//    }
//
//    /**
//     * 排序
//     * @param a
//     * @param start
//     * @param end
//     */
//    public void sort(int[] a, int start, int end){
//        if(start > end){
//            //如果只有一个元素，就不用再排下去了
//            return;
//        }
//        else{
//            //如果不止一个元素，继续划分两边递归排序下去
//            int partition = divide(a, start, end);
//            sort(a, start, partition-one);
//            sort(a, partition+one, end);
//        }
//
//    }






}
