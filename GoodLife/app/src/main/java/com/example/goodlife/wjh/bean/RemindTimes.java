package com.example.goodlife.wjh.bean;

import java.io.Serializable;

public class RemindTimes implements Serializable {

    private Integer rtId;
    private String time;
    private Integer flag;

    private boolean open;

    public RemindTimes() {
    }

    public RemindTimes(Integer rtId, String time, Integer flag) {
        this.rtId = rtId;
        this.time = time;
        this.flag = flag;
    }


    public Integer getRtId() {
        return rtId;
    }

    public void setRtId(Integer rtId) {
        this.rtId = rtId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    @Override
    public String toString() {
        return "RemindTimes{" +
                "rtId=" + rtId +
                ", time='" + time + '\'' +
                ", flag=" + flag +
                '}';
    }
}
