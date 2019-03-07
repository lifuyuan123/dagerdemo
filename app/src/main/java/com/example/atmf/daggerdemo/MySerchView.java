package com.example.atmf.daggerdemo;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.lang.ref.WeakReference;


public class MySerchView extends View {

    private int width,hight;
    private MyHandle handle;
    private float valueAnima=0;//当前动画值
    private ValueAnimator startValueAnimator,serchValueAnimator,endValueAnimator;//每个状态动画的数值发生器
    private ValueAnimator.AnimatorUpdateListener updateListener;//获取动画过程的一个值区间[0~one]
    private Animator.AnimatorListener animatorListener;//监听动画开始到结束
    private Statu nowStatu=Statu.NONULL;//当前状态
    private Path cirPath,serPath;
    private Paint paint;
    private PathMeasure pathMeasure;
    private boolean serchIsOver=true;

    public MySerchView(Context context) {
        super(context,null);
    }

    public MySerchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width=w/2;
        hight=h/2;
    }

    private void init() {

        initData();//初始化数据

        initNonllPath();//初始化状态path


        nowStatu=Statu.START;
        startValueAnimator.start();

    }

    //初始化状态path
    private void initNonllPath() {
        paint=new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeCap(Paint.Cap.ROUND);//圆形线冒
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(15);
        paint.setColor(Color.WHITE);

        cirPath=new Path();//外圆
        serPath=new Path();//ser图标

        //这里注意  画的是圆弧而不是圆  为了定位搜索图标的那个点
        //外圆
        RectF rectF=new RectF(-100,-100,100,100);
        cirPath.addArc(rectF,45,-359.9f);

        RectF serrectF=new RectF(-50,-50,50,50);
        serPath.addArc(serrectF,45,359.9f);

        float[] point=new float[2];//大圆闭合的点坐标
        pathMeasure=new PathMeasure();//初始化path的测量工具
        pathMeasure.setPath(cirPath,false);//设置一个需要测量的path  注意这里传入的是大圆的path
        pathMeasure.getPosTan(0,point,null);//获取大圆闭合位置的点坐标

        serPath.lineTo(point[0],point[1]);//path链接到大圆（外圆）的闭合点   相当于画出了放大镜的手柄
        Log.e("point",point[0]+"    "+point[1]);


    }

    //初始化数据
    private void initData() {
        handle=new MyHandle(this);

        //初始化监听
        updateListener=new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                valueAnima= (float) animation.getAnimatedValue();//获取当前的动画值
                invalidate();//这里注意刷新
            }
        };

        //动画状态监听
        animatorListener=new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) { }
            @Override
            public void onAnimationEnd(Animator animation) {
              //动画结束进行下一步操作  这里用handle发送事件
                handle.sendEmptyMessage(0);
            }
            @Override
            public void onAnimationCancel(Animator animation) { }
            @Override
            public void onAnimationRepeat(Animator animation) { }
        };


        //初始化及设置上监听
        startValueAnimator=ValueAnimator.ofFloat(0,1).setDuration(2000);
        serchValueAnimator=ValueAnimator.ofFloat(0,1).setDuration(2000);
        endValueAnimator=ValueAnimator.ofFloat(1,0).setDuration(2000);


        startValueAnimator.addUpdateListener(updateListener);
        serchValueAnimator.addUpdateListener(updateListener);
        endValueAnimator.addUpdateListener(updateListener);

        startValueAnimator.addListener(animatorListener);
        serchValueAnimator.addListener(animatorListener);
        endValueAnimator.addListener(animatorListener);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(width,hight);
        canvas.drawColor(Color.parseColor("#0082D7"));//画背景


        switch (nowStatu){
            case NONULL://初始状态，显示搜索图标
                canvas.drawPath(serPath,paint);
                break;
            case START://开始状态  放大镜消失的动画
                pathMeasure.setPath(serPath,false);//放入搜索的path
                Path path=new Path();
                float start=pathMeasure.getLength()*valueAnima;//开始截取位置距离 Path 起点的长度    valueAnima是从0-1的变量
                float end=pathMeasure.getLength();//结束截取位置距离 Path 起点的长度
                pathMeasure.getSegment(start,end,path,true);//截取path的局部，最后一个参数startWithMoveTo，起始点是否使用 moveTo	用于保证截取的 Path 第一个点位置不变
                canvas.drawPath(path,paint);//valueAnima是从0-1的变量  使得这里绘制出来的图形在变化
                break;
            case SERCH://搜索状态  放大镜消失，外圆圈展示转圈效果
                pathMeasure.setPath(cirPath,false);//加入外圆path
                Path path1=new Path();
                float end1=pathMeasure.getLength()*valueAnima;//结束截取位置距离 Path 起点的长度
                float start1= (float) (end1-(0.5-Math.abs(valueAnima-0.5))*200f);//开始截取位置距离 Path 起点的长度   这里使用了一个绝对值函数使得到的最终值和end1的插值呈现一个弧度增长和减少
                pathMeasure.getSegment(start1,end1,path1,true);
                canvas.drawPath(path1,paint);
                break;
            case END://外圆转圈停止并消失，显示搜索图标
                pathMeasure.setPath(serPath,false);//放入搜索的path
                Path path2=new Path();
                float start2=pathMeasure.getLength()*valueAnima;//开始截取位置距离 Path 起点的长度    valueAnima是从1-0的变量
                float end2=pathMeasure.getLength();//结束截取位置距离 Path 起点的长度
                Log.e("end","start:"+start2+"    end:"+end2);
                pathMeasure.getSegment(start2,end2,path2,true);//截取path的局部，最后一个参数startWithMoveTo，起始点是否使用 moveTo	用于保证截取的 Path 第一个点位置不变
                canvas.drawPath(path2,paint);//valueAnima是从1-0的变量  使得这里绘制出来的图形在变化
                break;
        }

    }



    //静态内部类使用弱引用防止内存溢出
    private static class MyHandle extends Handler {

        private WeakReference<MySerchView> mySerchViewWeakReference;
        private int cout=0;
        public MyHandle(MySerchView mySerchView) {
            mySerchViewWeakReference=new WeakReference<>(mySerchView);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
                switch (mySerchViewWeakReference.get().nowStatu){
                    case START://开始状态  放大镜消失的动画
                        mySerchViewWeakReference.get().serchIsOver=false;//是否完成搜索
                        mySerchViewWeakReference.get().nowStatu=Statu.SERCH;//改变状态  转为搜索
                        mySerchViewWeakReference.get().serchValueAnimator.start();//搜索数值发生器 开始
                        cout++;
                        break;
                    case SERCH://搜索状态  放大镜消失，外圆圈展示转圈效果
                        if (!mySerchViewWeakReference.get().serchIsOver){//搜索还未结束
                            cout++;
                            mySerchViewWeakReference.get().serchValueAnimator.start();//搜索数值发生器 继续
                            if (cout>2){
                                mySerchViewWeakReference.get().serchIsOver=true;//是否完成搜索
                            }
                            Log.e("statu1",cout+"   "+mySerchViewWeakReference.get().serchIsOver+"  "+mySerchViewWeakReference.get().nowStatu);
                        }else {//结束搜索
                            mySerchViewWeakReference.get().nowStatu=Statu.END;//改变状态  转为结束
                            mySerchViewWeakReference.get().endValueAnimator.start();//结束数值发生器 开始
                            Log.e("statu2",cout+"   "+mySerchViewWeakReference.get().serchIsOver+"  "+mySerchViewWeakReference.get().nowStatu);
                        }
                        break;
                    case END://外圆转圈停止并消失，显示搜索图标
                        mySerchViewWeakReference.get().nowStatu=Statu.NONULL;
                        break;
                }

        }
    }

    //当前状态
    public static enum Statu{
        NONULL,
        START,
        SERCH,
        END
    }
}
