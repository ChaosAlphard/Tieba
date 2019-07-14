package com.model;

public class User {
    public static final int BlockedUser = 0;    //封禁用户
    public static final int NormalUser = 1;     //普通用户
    public static final int BarService = 2;     //吧务
    public static final int Administrator = 3;  //管理员

    private int UID;
    private String account;
    private String username;
    private String password;
    private int adminLv;

    public int getUID() {
        return UID;
    }

    public void setUID(int UID) {
        this.UID = UID;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAdminLv() {
        return adminLv;
    }

    public void setAdminLv(int adminLv) {
        this.adminLv = adminLv;
    }
}