package com.example.atmf.daggerdemo.entry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by edz on 2018/five/11.
 */

//被观察者
public class Observerble<T> {

    //用于装观察者
    private List<Observer<T>> Observers=new ArrayList<>();

    //注册
    public void regiset(Observer<T> observer){
        if (observer!=null&&!Observers.contains(observer)){
            Observers.add(observer);
        }
    }

    //取消注册
    public void unRegist(Observer<T> observer){
        if (observer!=null&&Observers.contains(observer)){
            Observers.remove(observer);
        }
    }

    //更听
    public void updata(T data){
        for (Observer<T> observer:Observers) {
            observer.updata(data);
        }
    }
}
