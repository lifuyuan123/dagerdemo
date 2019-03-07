package com.example.atmf.daggerdemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.atmf.daggerdemo.R;
import com.example.atmf.daggerdemo.entry.EntryA;
import com.example.atmf.daggerdemo.entry.EntryC;
import com.example.atmf.daggerdemo.interfaces.DaggerFourActivityComponent;
import com.example.atmf.daggerdemo.moudle.EntryABMoudle;

import javax.inject.Inject;

public class FourActivity extends AppCompatActivity {

    @Inject
    EntryC entryC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);

        /**
         * 在Module的构造函数带有参数且参数被使用的情况下，
         * 所生产的Component类就没有create()方法了。
         * */
//        DaggerFourActivityComponent.builder()
//                .entryABMoudle(new EntryABMoudle(new EntryA())).build()//moudle传参entryA
//                .inject(this);

        if (entryC==null){
            ((TextView)findViewById(R.id.tv4_hello)).setText("复杂moudle失败");
        }else {
            ((TextView)findViewById(R.id.tv4_hello)).setText("复杂moudle成功");
        }



    }

}
