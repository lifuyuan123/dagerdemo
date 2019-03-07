package com.example.atmf.daggerdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Spider extends View {

    private int width,hight;
    private Paint paint,textpain,valuePain;
    private int count=6;
    private float cR;
    private float mx;//每一层的半径差值
    private String [] titles={"空间能力","计算能力","逻辑能力","想象能力","动手能力","感知能力"};
    private double[] data = {80,75,70,60,90,53,19,69}; //各维度分值


    public Spider(Context context) {
        super(context,null);
    }

    public Spider(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        cR= (float) (Math.min(w,h)*0.7);
        mx=cR/2/5;
        width=w;
        hight=h;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(width/2,hight/2);
        drawSpider(canvas);
        drawLine(canvas);
        drawText(canvas);
        drawValue(canvas);

    }

    //画直线
    private void drawLine(Canvas canvas) {
        Paint paint=new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeMiter(1);
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);

        //使用path
//        Path path=new Path();
//        for (int i = 0; i < six; i++) {
//            path.moveTo(0,0);
//            float x= (float) (mx*five*Math.cos(Math.PI*two/count*i));
//            float y= (float) (mx*five*Math.sin(Math.PI*two/count*i));
//            path.lineTo(x,y);
//
//        }
//        canvas.drawPath(path,paint);

       //使用画布,旋转角度
        for (int i = 0; i < 6; i++) {
            canvas.drawLine(0,0,mx*5,0,paint);
            canvas.rotate(60);
        }
    }

    //画值
    private void drawValue(Canvas canvas) {
        Paint paint=new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(Color.parseColor("#0000FF"));
        paint.setAlpha(255);//数字越大越不透明
        Path path=new Path();

        for (int i = 0; i < 6; i++) {
            float x= (float) (data[i]*Math.cos(Math.PI*2/count*i)*mx*5/100);//按照百分比来分配   如果data[i]==100,则全部沾满
            float y= (float) (data[i]*Math.sin(Math.PI*2/count*i)*mx*5/100);
            if (i==0){
                path.moveTo(x,y);//第一个点是移动到点的位置
            }else {//其他点依次链接起来
               path.lineTo(x,y);
            }
            canvas.drawCircle(x,y,10, paint);//画小圆点

        }
//        canvas.drawPath(path,paint);//画小圆点

        paint.setColor(Color.parseColor("#7D7DFC"));
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setAlpha(90);//数字越小越透明
        canvas.drawPath(path,paint);
    }

    //画文字
    private void drawText(Canvas canvas) {

        Paint paint=new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setTextSize(26);
        paint.setColor(Color.BLACK);

        for (int i = 0; i < 6; i++) {

            Paint.FontMetrics fontMetrics = paint.getFontMetrics();
            float fontHeight = fontMetrics.descent - fontMetrics.ascent;
            float width=paint.measureText(titles[i]);
            if (Math.PI*2/count>0&&Math.PI*2/count*i==0){//0度  右边那个点
                canvas.drawText(titles[i],(float) (mx*5*Math.cos(Math.PI*2/count*i))+width/titles.length/2,(float) (mx*5*Math.sin(Math.PI*2/count*i))+fontHeight/4,paint);//再次强调cos（）和sin()里面放的是弧度（2Π）不是角度
            }else if(Math.PI*2/count>0&&Math.PI*2/count*i<Math.PI/2){//右下角那个象限
                canvas.drawText(titles[i],(float) (mx*5*Math.cos(Math.PI*2/count*i)-width/2),(float) (mx*5*Math.sin(Math.PI*2/count*i))+fontHeight/4*5,paint);//再次强调cos（）和sin()里面放的是弧度（2Π）不是角度
            } else if(Math.PI*2/count>0&&Math.PI*2/count*i<Math.PI){//左下角
                canvas.drawText(titles[i],(float) (mx*5*Math.cos(Math.PI*2/count*i)-width/2),(float) (mx*5*Math.sin(Math.PI*2/count*i))+fontHeight/4*5,paint);//再次强调cos（）和sin()里面放的是弧度（2Π）不是角度
            }else if(Math.PI*2/count>0&&Math.PI*2/count*i==Math.PI){//左边
                canvas.drawText(titles[i],(float) (mx*5*Math.cos(Math.PI*2/count*i))-width-width/titles.length/2,(float) (mx*5*Math.sin(Math.PI*2/count*i)+fontHeight/4),paint);//再次强调cos（）和sin()里面放的是弧度（2Π）不是角度
            }else if(Math.PI*2/count>0&&Math.PI*2/count*i<Math.PI/2*3){//左上角
                canvas.drawText(titles[i],(float) (mx*5*Math.cos(Math.PI*2/count*i)-width/2),(float) (mx*5*Math.sin(Math.PI*2/count*i)-fontHeight/2),paint);//再次强调cos（）和sin()里面放的是弧度（2Π）不是角度
            }else if (Math.PI*2/count>0&&Math.PI*2/count*i<Math.PI*2){//右上角
                canvas.drawText(titles[i],(float) (mx*5*Math.cos(Math.PI*2/count*i)-width/2),(float) (mx*5*Math.sin(Math.PI*2/count*i)-fontHeight/2),paint);//再次强调cos（）和sin()里面放的是弧度（2Π）不是角度
            }
        }
    }


    //画蜘蛛网
    private void drawSpider(Canvas canvas) {
        paint=new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeMiter(2);
        paint.setPathEffect ( new DashPathEffect( new float [ ] { 3, 3 }, 0 ) ) ;//虚线
        paint.setAntiAlias(true);
        Path path=new Path();

        for (int j = 1; j < 6; j++) {//第一圈是一个点  不用画
              float r=mx*j;
            for (int i = 0; i < 6; i++) {
                if (i==0){
                    path.moveTo(r,0);//移动点到最右边的位置（r,0）
                }else {
                    float x= (float) (r*Math.cos(Math.PI*2/count*i));//通过余弦获取x值
                    float y= (float) (r*Math.sin(Math.PI*2/count*i));//通过正弦获取y值
                    path.lineTo(x,y);
                }

            }
            path.close();//闭合
        }
        canvas.drawPath(path,paint);
    }
}
