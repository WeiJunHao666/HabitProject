package com.example.goodlife.wjh.homepage;

import com.example.goodlife.ConnectUtil;
import com.example.goodlife.wjh.MyListener;
import com.example.goodlife.wjh.MyModel;


public class HomePageModel implements MyModel {

    @Override
    public void show(String url, MyListener listener) {
        ConnectUtil.connectByUrl(url, listener);
    }

    @Override
    public void login(String url, String json, MyListener listener) {

    }

    @Override
    public void register(String url, String json, MyListener listener) {

    }

    @Override
    public void setHabit(String url, String json, MyListener listener) {
    }

    @Override
    public void delete(String url, MyListener listener) {

    }
}
