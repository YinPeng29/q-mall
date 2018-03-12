package com.bays.model;

import java.util.Date;

public class User {
    private int id;
    private String userId;
    private String userName;
    private String passWord;
    private String email;
    private int status;
    private Date addTime;
    private Date latest_login_time;
    private String uuid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getLatest_login_time() {
        return latest_login_time;
    }

    public void setLatest_login_time(Date latest_login_time) {
        this.latest_login_time = latest_login_time;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                ", addTime=" + addTime +
                ", latest_login_time=" + latest_login_time +
                ", uuid=" + uuid +
                '}';
    }
}
