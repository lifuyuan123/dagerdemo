package com.example.atmf.daggerdemo.moudle;

import com.example.atmf.daggerdemo.entry.Cloth;
import com.example.atmf.daggerdemo.entry.Food;

import dagger.Module;
import dagger.Provides;

/**
 * Created by edz on 2018/five/8.
 */

@Module
public class PackModle {

    @Provides
    Food getFood(){
        return new Food();
    }

    @Provides
    Cloth getCloth(){
        return new Cloth();
    }
}
