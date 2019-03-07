package com.example.atmf.daggerdemo.interfaces;

import com.example.atmf.daggerdemo.activity.ThreeActivity;
import com.example.atmf.daggerdemo.entry.EntryB;
import com.example.atmf.daggerdemo.moudle.EntryBMoudle;

import dagger.Component;

/**
 * Created by edz on 2018/three/28.
 */


/**
 * 编写Component接口，使用@Component标注这个接口，
 * 并使用modules=的方法链接上第一步中编写的Module类
 * */
@Component(modules = EntryBMoudle.class)
public interface ThreeActivityComponent {
    void inject(ThreeActivity threeActivity);
//      EntryB moudleGetEntryB();
}
