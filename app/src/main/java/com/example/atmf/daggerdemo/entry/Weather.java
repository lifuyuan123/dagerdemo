package com.example.atmf.daggerdemo.entry;

/**
 * Created by edz on 2018/five/11.
 */


//天气实体   相当于传递的信息
public class Weather {

    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Weather(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "data='" + data + '\'' +
                '}';
    }
}
