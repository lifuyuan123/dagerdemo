package com.example.atmf.daggerdemo.entry;

/**
 * Created by edz on 2018/five/11.
 */


//观察者需定义为泛型  因为观察者种类可能很多  还需要定义一个更新的方法
public interface Observer<T> {
    void updata(T data);
}
