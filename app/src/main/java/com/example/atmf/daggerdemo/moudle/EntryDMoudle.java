package com.example.atmf.daggerdemo.moudle;

import com.example.atmf.daggerdemo.activity.FiveActivity;
import com.example.atmf.daggerdemo.entry.EntryD;

import dagger.Module;
import dagger.Provides;

/**
 * Created by edz on 2018/three/28.
 */

@Module
public class EntryDMoudle {

    @Provides
    EntryD entryDMoudleGetD(){
        return new EntryD();
    }
}
