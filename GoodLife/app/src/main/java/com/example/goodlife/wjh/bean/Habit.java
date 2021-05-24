package com.example.goodlife.wjh.bean;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Habit {
    private Integer habitId;
    private Integer color;
    private Integer icon;
    private String name;
    private Integer timeOfDay;
    private String dayOfWeek;
    private Integer keepDays;
    private Integer continueDays;
    private Integer maxContinueDays;

    private boolean open;

    private Date createDate;
    private String encourageWords;

    private List<RemindTimes> remindTimes = new ArrayList<RemindTimes>();

    private List<ClockIn> clockIns = new ArrayList<ClockIn>();

    public Habit() {
    }

    public Habit(Integer habitId, Integer color, Integer icon, String name, Integer timeOfDay, String dayOfWeek, Integer keepDays, Integer continueDays, Integer maxContinueDays, Date createDate, String encourageWords, List<RemindTimes> remindTimes, List<ClockIn> clockIns) {
        this.habitId = habitId;
        this.color = color;
        this.icon = icon;
        this.name = name;
        this.timeOfDay = timeOfDay;
        this.dayOfWeek = dayOfWeek;
        this.keepDays = keepDays;
        this.continueDays = continueDays;
        this.maxContinueDays = maxContinueDays;
        this.createDate = createDate;
        this.encourageWords = encourageWords;
        this.remindTimes = remindTimes;
        this.clockIns = clockIns;
    }

    public Integer getHabitId() {
        return habitId;
    }

    public void setHabitId(Integer habitId) {
        this.habitId = habitId;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }


    public Integer getIcon() {
        return icon;
    }

    public void setIcon(Integer icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTimeOfDay() {
        return timeOfDay;
    }

    public void setTimeOfDay(Integer timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Integer getKeepDays() {
        return keepDays;
    }

    public void setKeepDays(Integer keepDays) {
        this.keepDays = keepDays;
    }

    public Integer getContinueDays() {
        return continueDays;
    }

    public void setContinueDays(Integer continueDays) {
        this.continueDays = continueDays;
    }

    public Integer getMaxContinueDays() {
        return maxContinueDays;
    }

    public void setMaxContinueDays(Integer maxContinueDays) {
        this.maxContinueDays = maxContinueDays;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getEncourageWords() {
        return encourageWords;
    }

    public void setEncourageWords(String encourageWords) {
        this.encourageWords = encourageWords;
    }

    public List<RemindTimes> getRemindTimes() {
        return remindTimes;
    }

    public void setRemindTimes(List<RemindTimes> remindTimes) {
        this.remindTimes = remindTimes;
    }

    public List<ClockIn> getClockIns() {
        return clockIns;
    }

    public void setClockIns(List<ClockIn> clockIns) {
        this.clockIns = clockIns;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
}
