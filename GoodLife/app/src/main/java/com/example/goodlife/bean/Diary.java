package com.example.goodlife.bean;

public class Diary {
    private int icon;
    private String date;
    private String time;
    private String content;

    public Diary(int icon, String date, String time, String content) {
        this.icon = icon;
        this.date = date;
        this.time = time;
        this.content = content;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
