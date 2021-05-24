package com.example.goodlife.wjh;

public interface MyModel {

    //登录 √
    void login(String url , String json, MyListener listener);

    //注册 √
    void register(String url,String json,MyListener listener);

    //设置习惯的属性 √
    void setHabit(String url, String json, MyListener listener);

    //展示习惯 √
    void show(String url, MyListener listener);

    //删除习惯 √
    void delete(String url, MyListener listener);

    //---------------首页--------------------
    //打卡

    //取消打卡

    //查看习惯详情

    //记录日志内容

    //---------------日常动态--------------------
    //展示选择日期的动态

    //---------------习惯详情--------------------
    //
}
