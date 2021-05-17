package com.example.goodlife.bean;

public class Remind {

    private String time;
    private int flag;

    public Remind(String time, int flag) {
        this.time = time;
        this.flag = flag;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
