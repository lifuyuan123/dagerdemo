package com.example.atmf.daggerdemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.example.atmf.daggerdemo.R;
import com.example.atmf.daggerdemo.entry.EntryA;
import com.example.atmf.daggerdemo.interfaces.DaggerMainActivityComponent;

import javax.inject.Inject;
//不带构造参数不带moudle的实例方式
public class MainActivity extends AppCompatActivity {

    //在需要注入的类中使用@Inject标注要注入的变量（使用的位置申明不能是私有的）
    @Inject
    EntryA entryA;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.tv_content);
        DaggerMainActivityComponent.create().inject(this);
//        if (entryA==null){
//            ((TextView)findViewById(R.id.tv_hello)).setText("dagger生成失败");
//        }else {
//            ((TextView)findViewById(R.id.tv_hello)).setText("dagger生成完毕");
//        }


        String s="<font color='#112da9'>默认颜色</font><font color='#FF0000'><small>红颜色</small></font>";
        textView.setTextSize(20);
        textView.setText(Html.fromHtml(s));

    }
}
