package com.example.atmf.daggerdemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.atmf.daggerdemo.R;
import com.example.atmf.daggerdemo.entry.EntryB;
import com.example.atmf.daggerdemo.interfaces.DaggerThreeActivityComponent;

import javax.inject.Inject;



/**
 * 简单Module使用
 *
 * 直接给构造函数添加@Inject标注的方式对于我们自己编写的代码肯定是没问题，
 * 但若是我们引入的第三方库不能随意改动代码的话就不方便了
 * 这种情况下就需要用到Module了
 *
 * 不可更改代码需要inject要用module
 * */
public class ThreeActivity extends AppCompatActivity {
    @Inject
    EntryB entryB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);
        DaggerThreeActivityComponent.create().inject(this);
        if (entryB==null){
            ((TextView)findViewById(R.id.tv3_hello)).setText("dagger生成modler里的第三方类失败");
        }else {
            ((TextView)findViewById(R.id.tv3_hello)).setText("dagger生成modler里的第三方类成功");

        }
    }
}
