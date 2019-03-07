package com.example.atmf.daggerdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Xfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class RoudView extends View {
    private int width,hight;
    private float cR;
    private Xfermode mXfermode;
    private PorterDuff.Mode mPorterDuffMode = PorterDuff.Mode.XOR;

    public RoudView(Context context) {
        super(context,null);
    }

    public RoudView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
    }

    public RoudView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        cR= (float) (Math.min(w,h)*0.7);
        width=w;
        hight=h;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(width/2,hight/2);




    }
}
