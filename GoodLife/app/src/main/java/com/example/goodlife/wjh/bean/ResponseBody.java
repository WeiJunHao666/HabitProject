package com.example.goodlife.wjh.bean;

public class ResponseBody {
    private int code;
    private String data;

    public ResponseBody() {
    }

    public ResponseBody(int code, String data) {
        this.code = code;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
