package com.example.atmf.daggerdemo.interfaces;

import android.app.Activity;

import com.example.atmf.daggerdemo.activity.SubComponentntActivity;
import com.example.atmf.daggerdemo.moudle.PackModle;
import com.example.atmf.daggerdemo.moudle.UserModle;

import dagger.Component;
import dagger.Subcomponent;

/**
 * Created by edz on 2018/five/8.
 */
@Subcomponent(modules = UserModle.class)
public interface UserComponent {
    void inject(SubComponentntActivity activity);
}
