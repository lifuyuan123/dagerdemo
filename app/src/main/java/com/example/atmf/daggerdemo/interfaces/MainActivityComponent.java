package com.example.atmf.daggerdemo.interfaces;

import com.example.atmf.daggerdemo.activity.MainActivity;

import dagger.Component;

/**
 * Created by edz on 2018/three/28.
 */


//接口使用@Component进行标注(Component就是注入者与被注入者之间联系的桥梁，有了它dagger2才知道要把谁注入到什么地方，所以它是非常重要且不可缺少的)
@Component
public interface  MainActivityComponent {

    //参数表示要将依赖注入到的目标位置(当前是注解到MainActivity)
    void inject(MainActivity mainActivity);
}
