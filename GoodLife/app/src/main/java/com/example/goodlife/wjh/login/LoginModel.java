package com.example.goodlife.wjh.login;

import com.example.goodlife.ConnectUtil;
import com.example.goodlife.wjh.MyListener;
import com.example.goodlife.wjh.MyModel;

public class LoginModel implements MyModel {

    @Override
    public void login(String url , String json, MyListener listener) {
        ConnectUtil.connectByJson(url, json, listener);
    }

    @Override
    public void register(String url, String json, MyListener listener) {

    }

    @Override
    public void setHabit(String url, String json, MyListener listener) {

    }

    @Override
    public void show(String url, MyListener listener) {

    }

    @Override
    public void delete(String url, MyListener listener) {

    }
}
