package com.example.atmf.daggerdemo.entry;

import android.util.Log;

import javax.inject.Inject;

/**
 * Created by edz on 2018/three/28.
 */

public class EntryA {
    //需要注入的对象的类的构造参数使用@Inject标注，告诉dagger2它可以实例化这个类
    @Inject
    public EntryA() {
        Log.e("EntryA","a被实例化了");
    }
}
