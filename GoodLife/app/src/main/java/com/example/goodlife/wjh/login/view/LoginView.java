package com.example.goodlife.wjh.login.view;


public interface LoginView {

    /**
     * 展示消息
     * @param message
     */
    void onFailure(String message);

    /**
     * 成功
     * @param data
     */
    void onSuccess(String data);


}

