package com.example.goodlife.wjh;

public interface MyView {

    /**
     * 失败
     * @param message
     */
    void onFailure(String message);

    /**
     * 成功
     * @param data
     */
    void onSuccess(String data);
    /**
     * 第二种网络请求成功
     */
    void onSuccess2(String data);
}
