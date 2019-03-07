package com.example.atmf.daggerdemo.moudle;

/**
 * Created by edz on 2018/three/28.
 */

import com.example.atmf.daggerdemo.entry.EntryB;

import dagger.Module;
import dagger.Provides;

/**
 * 编写Module类并使用@Module标注这个类，
 * 编写方法返回值为我们需要inject的类型并使用@Provides标注这个方法；
 * */

@Module
public class EntryBMoudle {
    //编写方法返回值为我们需要inject的类型并使用@Provides标注这个方法；
    @Provides
    EntryB moudleGetEntryB(){
        return new EntryB();
    }
}
