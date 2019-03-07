package com.example.atmf.daggerdemo.view;

import com.alibaba.android.arouter.facade.template.IProvider;

public interface HelloService extends IProvider {
    int getid(int id);
}