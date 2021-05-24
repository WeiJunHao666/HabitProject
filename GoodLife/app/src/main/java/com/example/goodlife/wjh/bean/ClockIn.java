package com.example.goodlife.wjh.bean;


import java.util.Date;

public class ClockIn {
    private Integer clockId;
    private Date clockDate;
    private String clockLog;

    public ClockIn(Integer clockId, Date clockDate, String clockLog) {
        this.clockId = clockId;
        this.clockDate = clockDate;
        this.clockLog = clockLog;
    }

    public ClockIn() {
    }

    public Integer getClockId() {
        return clockId;
    }

    public void setClockId(Integer clockId) {
        this.clockId = clockId;
    }

    public Date getClockDate() {
        return clockDate;
    }

    public void setClockDate(Date clockDate) {
        this.clockDate = clockDate;
    }

    public String getClockLog() {
        return clockLog;
    }

    public void setClockLog(String clockLog) {
        this.clockLog = clockLog;
    }

    @Override
    public String toString() {
        return "ClockIn{" +
                "clockId=" + clockId +
                ", clockDate=" + clockDate +
                ", clockLog='" + clockLog + '\'' +
                '}';
    }
}
