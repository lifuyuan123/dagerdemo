package com.example.atmf.daggerdemo.entry;

import android.util.Log;

import javax.inject.Inject;

/**
 * Created by edz on 2018/three/28.
 */

//带参数的类
public class EntryB {
    EntryA entryA;

    @Inject
    public EntryB(EntryA entryA) {
        this.entryA = entryA;
        Log.e("EntryB","b被实例化了有参");
    }

    public EntryB(){
        Log.e("EntryB","b被实例化了");

    }

    public EntryA getEntryA() {
        return entryA;
    }

    public void setEntryA(EntryA entryA) {
        this.entryA = entryA;
    }
}
