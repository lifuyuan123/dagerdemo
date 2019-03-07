package com.example.atmf.daggerdemo.moudle;

import com.example.atmf.daggerdemo.entry.EntryA;
import com.example.atmf.daggerdemo.entry.EntryB;
import com.example.atmf.daggerdemo.entry.EntryC;

import dagger.Module;
import dagger.Provides;

/**
 * Created by edz on 2018/three/28.
 */

/**
 * 复杂Module使用
 *
 * 如果我们希望在使用的时候才传入一些配置，
 * 直接使用Module的构造参数传入即可，
 * 这种用法注意Activity中Component实例化的时候使用builder模式传入了我们需要传入的值；
 *
 * */
@Module
public class EntryABMoudle {

    private EntryA entryA;

    public EntryABMoudle(EntryA entryA) {
        this.entryA = entryA;
    }


    /**
     * Module中其中一个依赖又要依赖另外一个依赖，
     * 如果被@Provides标注的方法带有参数，
     * dagger2会自动寻找本Module中其他返回值类型为参数的类型的且被@Provides标注的方法，
     * 如果本Module中找不到就会去看这个类的构造参数是否被@Inject标注了
     *
     * （所以一般情况下Module中方法的返回值都不能相同，当然也有办法使多个方法的返回值类型相同，
     * 有需要的朋友请自行研究吧，本篇只讲解基础上手）；
     * */
    @Provides
    EntryC entryABmoudleGetC(EntryB entryB){
        return new EntryC(entryB);
    }


    //如上所述  关闭这段代码  dagger会查看上面方法参数的构造方法是否被@Inject标注了
    //打开这段代码  dagger则会先使用此方法返回的EntryB实例传入到上面的方法中
    //    @Provides
//    EntryB entryABmoudleGetB(){
//        EntryB entryB = new EntryB();
//        entryB.setEntryA(entryA);
//        return entryB;
//    }
}
