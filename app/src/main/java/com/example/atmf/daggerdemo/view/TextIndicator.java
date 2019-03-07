package com.example.atmf.daggerdemo.view;

import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import net.moyokoo.diooto.interfaces.IIndicator;
import net.moyokoo.diooto.tools.Utils;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class TextIndicator implements IIndicator {

    private int originBottomMargin = 10;
    private int count=0;//总共页数
    private int firstIndext=0;//第一次进入下标
    private TextView textView;

    @Override
    public void attach(FrameLayout parent) {

        //设置布局位置
        originBottomMargin = Utils.dip2px(parent.getContext(), 16);
        FrameLayout.LayoutParams indexLp = new FrameLayout.LayoutParams(WRAP_CONTENT, Utils.dip2px(parent.getContext(), 36));
        indexLp.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
        indexLp.bottomMargin = originBottomMargin;

        //设置text相应参数
        textView=new TextView(parent.getContext());
        textView.setText("1/6");
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
        textView.setTextColor(Color.parseColor("#ffffff"));
        textView.setGravity(Gravity.CENTER_HORIZONTAL);
        textView.setLayoutParams(indexLp);


        parent.addView(textView);
    }

    @Override
    public void onShow(ViewPager viewPager) {
        count=viewPager.getAdapter().getCount();
        firstIndext=viewPager.getCurrentItem();
        textView.setText(String.format(firstIndext+1+"/"+count));//第一次进入的下标设置
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                textView.setText(String.format(position+1+"/"+count));//下标变化的设置
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    }

    @Override
    public void move(float v, float v1) {
    }

    @Override
    public void fingerRelease(boolean b, boolean b1) {
    }
}
