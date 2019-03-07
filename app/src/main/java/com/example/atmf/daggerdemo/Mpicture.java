package com.example.atmf.daggerdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.lang.ref.WeakReference;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class Mpicture extends View {

    private Bitmap bitmap=null;
    private Paint paint=null;
    private Picture picture=null;
    private NoLeakHandler handler;
    private int sum=13;


    public Mpicture(Context context) {
        super(context);
    }

    public Mpicture(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
//        begin();
        handler=new NoLeakHandler(this);
        start();
    }


    public void start(){
        handler.sendEmptyMessageDelayed(0,100);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        picture.draw(canvas);//会改变canvas状态
//        Rect rect=new Rect(0,0,picture.getWidth(),200);
//        canvas.drawPicture(picture,rect);

        bitmap= BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
        canvas.translate(getWidth()/2,getHeight()/2);
        Rect rect1=new Rect(0,0,bitmap.getWidth()/2,bitmap.getHeight()/2);//截取图片区域
        RectF rectF=new RectF(-bitmap.getWidth()/2,-bitmap.getHeight()/2,bitmap.getWidth()/2,bitmap.getHeight()/2);//显示在界面区域
//        canvas.drawBitmap(bitmap,rect1,rectF,new Paint());

        show(canvas);
    }

    private void begin() {

        picture=new Picture();
        Canvas canvas=picture.beginRecording(500,500);//开始录制
        canvas.translate(250,250);


        paint=new Paint();
        paint.setColor(Color.BLUE);
        paint.setAntiAlias(true);//抗锯齿
        paint.setStyle(Paint.Style.FILL);

        canvas.drawCircle(0,0,100,paint);
        picture.endRecording();//结束录制
    }

    void show(Canvas canvas){

           Paint paint=new Paint();
           paint.setStyle(Paint.Style.FILL);
           paint.setColor(Color.GREEN);
           paint.setAntiAlias(true);

           canvas.drawCircle(0,0,120,paint);

            Bitmap bitmap=BitmapFactory.decodeResource(getResources(),R.drawable.checkmark);
            Rect rect=new Rect(handler.getPage()*bitmap.getWidth()/sum,0,(handler.getPage()+1)*bitmap.getWidth()/sum,bitmap.getHeight());
            RectF rectF=new RectF(-50,-50,50,50);
            canvas.drawBitmap(bitmap,rect,rectF,null);


    }

    private static class NoLeakHandler extends android.os.Handler{
        private WeakReference<Mpicture> mpictureWeakReference;
        private int page=-2;

        public int getPage() {
            return page;
        }

        public NoLeakHandler(Mpicture mpicture) {
            mpictureWeakReference = new WeakReference<>(mpicture);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                   mpictureWeakReference.get().invalidate();//刷新
                    page++;
                    if (page>=12){
                        return;
                    }
                    sendEmptyMessageDelayed(0,100);

                    break;
            }
        }
        }

}
