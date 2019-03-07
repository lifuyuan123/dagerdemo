package com.example.atmf.daggerdemo.interfaces;

import android.app.Activity;

import com.example.atmf.daggerdemo.entry.Cloth;
import com.example.atmf.daggerdemo.moudle.PackModle;
import com.example.atmf.daggerdemo.moudle.UserModle;

import dagger.Component;

/**
 * Created by edz on 2018/five/8.
 */


@Component(modules = PackModle.class)
public interface PackComponet {
//    Cloth getCloth();
    UserComponent getUserComponent(UserModle userModle);
}
