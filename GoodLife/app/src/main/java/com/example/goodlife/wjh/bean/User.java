package com.example.goodlife.wjh.bean;

import java.util.ArrayList;
import java.util.List;

public class User {
    private Integer userId;
    private String account;
    private String pwd;
    private String username;

    private List<Habit> habits = new ArrayList<Habit>();

    public User() {
    }

    public User(Integer userId, String account, String pwd, String username, List<Habit> habits) {
        this.userId = userId;
        this.account = account;
        this.pwd = pwd;
        this.username = username;
        this.habits = habits;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Habit> getHabits() {
        return habits;
    }

    public void setHabits(List<Habit> habits) {
        this.habits = habits;
    }

}
