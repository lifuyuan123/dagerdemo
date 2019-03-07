package com.example.atmf.daggerdemo.view;

import android.content.Context;
import android.view.View;
import android.widget.Toast;
import com.example.atmf.baselibrary.HelloService;

import com.alibaba.android.arouter.facade.annotation.Route;

@Route(path = "/service/hello", name = "测试服务")
public class HelloServiceImpl implements HelloService {
    private Context context;
    @Override
    public int getid(int id) {
        Toast.makeText(context, "id:"+id, Toast.LENGTH_SHORT).show();
    return id;
    }

    @Override
    public void init(Context context) {
      this.context=context;
    }
}
