package com.example.atmf.daggerdemo.interfaces;

import com.example.atmf.daggerdemo.activity.TwoActivity;

import dagger.Component;

/**
 * Created by edz on 2018/three/28.
 */

@Component
public interface TwoActivityComponent {
    void inject(TwoActivity twoActivity);

}
