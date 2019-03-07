package com.example.atmf.daggerdemo.interfaces;

import com.example.atmf.daggerdemo.activity.FiveActivity;
import com.example.atmf.daggerdemo.moudle.EntryABMoudle;
import com.example.atmf.daggerdemo.moudle.EntryBMoudle;
import com.example.atmf.daggerdemo.moudle.EntryDMoudle;

import dagger.Component;

/**
 * Created by edz on 2018/three/28.
 */


//注意  EntryDMoudle是自己的moudle   后面依赖的是ThreeActivityComponent.class
@Component(modules = {EntryDMoudle.class},dependencies = {FourActivityComponent.class})
public interface  FiveActivityComponent {
    void inject(FiveActivity activity);
}
