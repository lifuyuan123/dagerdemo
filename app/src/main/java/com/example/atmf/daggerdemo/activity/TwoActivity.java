package com.example.atmf.daggerdemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.atmf.daggerdemo.R;
import com.example.atmf.daggerdemo.entry.EntryB;
import com.example.atmf.daggerdemo.interfaces.DaggerMainActivityComponent;
import com.example.atmf.daggerdemo.interfaces.DaggerTwoActivityComponent;

import javax.inject.Inject;

//带构造参数不带moudle的实例方式
public class TwoActivity extends AppCompatActivity {

    /**
     * 进行inject()的时候，发现EntryB的构造函数被@Inject标注了且带有一个参数，
     * 然后dagger2就去寻找EntryA发现它的构造函数也被@Inject标注并且无参数，
     * 于是dagger2把EntryA的实例注入给FactoryActivity，
     * 然后再去实例化EntryB的时候用的是已经注入给FactoryActivity的那个EntryA实例。
     * 也就是说我们可以这样理解：并不是EntryB直接实例化EntryA，
     * 而是FactoryActivity实例化EntryA后交给EntryB使用的。
     * */
    @Inject
    EntryB entryB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        DaggerTwoActivityComponent.create().inject(this);

        if (entryB==null){
            ((TextView)findViewById(R.id.tv2_hello)).setText("dagger生成有参数失败");
        }else {
            ((TextView)findViewById(R.id.tv2_hello)).setText("dagger生成有参数成功"+"   entryB:"+entryB.hashCode()+"   entryA:"+entryB.getEntryA().hashCode());

        }
    }
}
