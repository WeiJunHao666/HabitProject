package com.example.goodlife.wjh.sethabit;


import com.example.goodlife.ConnectUtil;
import com.example.goodlife.wjh.MyModel;
import com.example.goodlife.wjh.MyListener;


public class SetHabitModel implements MyModel {

    @Override
    public void setHabit(String url, String json, MyListener listener) {
        ConnectUtil.connectByJson(url, json, listener);
    }

    @Override
    public void login(String url, String json, MyListener listener) {

    }

    @Override
    public void register(String url, String json, MyListener listener) {

    }

    @Override
    public void show(String url, MyListener listener) {

    }

    @Override
    public void delete(String url, MyListener listener) {

    }

}
