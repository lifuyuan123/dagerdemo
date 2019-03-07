package com.example.atmf.daggerdemo.interfaces;

import com.example.atmf.daggerdemo.activity.FourActivity;
import com.example.atmf.daggerdemo.entry.EntryB;
import com.example.atmf.daggerdemo.entry.EntryC;
import com.example.atmf.daggerdemo.moudle.EntryABMoudle;

import dagger.Component;
import dagger.Module;

/**
 * Created by edz on 2018/three/28.
 */

@Component(modules = EntryABMoudle.class)
public interface FourActivityComponent {
//    void inject(FourActivity fourActivity);
     EntryC entryABmoudleGetC();
}
