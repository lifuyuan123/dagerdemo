package com.example.atmf.daggerdemo.moudle;

import com.example.atmf.daggerdemo.entry.User;

import dagger.Module;
import dagger.Provides;

/**
 * Created by edz on 2018/five/8.
 */
@Module
public class UserModle {

    @Provides
    User getUser(){
        return new User();
    }


}
