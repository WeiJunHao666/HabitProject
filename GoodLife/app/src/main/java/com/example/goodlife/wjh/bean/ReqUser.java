package com.example.goodlife.wjh.bean;

public class ReqUser {

    private String account;

    private String pwd;

    private String username;

    public ReqUser() {

    }

    public ReqUser(String account, String pwd, String username) {
        this.account = account;
        this.pwd = pwd;
        this.username = username;
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
}
