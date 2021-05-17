package com.example.goodlife.wjh.bean;

import android.widget.LinearLayout;

public class Judge {
    private int flag;
    private String time;
    private LinearLayout layout;

    public Judge(int flag, String time, LinearLayout layout) {
        this.flag = flag;
        this.time = time;
        this.layout = layout;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public LinearLayout getLayout() {
        return layout;
    }

    public void setLayout(LinearLayout layout) {
        this.layout = layout;
    }
}
