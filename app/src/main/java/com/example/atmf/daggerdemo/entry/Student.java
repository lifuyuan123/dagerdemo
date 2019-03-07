package com.example.atmf.daggerdemo.entry;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class Student implements Serializable{

    private String name;
    private String age;
    private String sex;
    private Course[] clazz;
    private int hight;

    public Student(String name, String age, String sex, Course[] clazz,int hight) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.clazz = clazz;
        this.hight=hight;
    }

    public int getHight() {
        return hight;
    }

    public void setHight(int hight) {
        this.hight = hight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Course[] getClazz() {
        return clazz;
    }

    public void setClazz(Course[] clazz) {
        this.clazz = clazz;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", sex='" + sex + '\'' +
                ", clazz=" + Arrays.toString(clazz) +
                ", hight=" + hight +
                '}';
    }
}
