package com.tieba.model;

import java.util.Objects;

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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("UID=").append(UID);
        sb.append(", account='").append(account).append('\'');
        sb.append(", username='").append(username).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", adminLv=").append(adminLv);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if(this == o)
            return true;
        if(o == null || getClass() != o.getClass())
            return false;
        User user = (User) o;
        return UID == user.UID && adminLv == user.adminLv && Objects.equals(account, user.account) && Objects.equals(username, user.username) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(UID, account, username, password, adminLv);
    }
}