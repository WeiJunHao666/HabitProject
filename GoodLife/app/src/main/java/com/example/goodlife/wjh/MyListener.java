package com.example.goodlife.wjh;

public interface MyListener {
    /**
     * 失败
     * @param msg
     */
    void onFailure(String msg);

    /**
     * 成功
     * @param msg
     */
    void onSuccess(String msg);
}
