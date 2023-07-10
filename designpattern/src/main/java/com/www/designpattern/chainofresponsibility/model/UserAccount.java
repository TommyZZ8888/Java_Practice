package com.www.designpattern.chainofresponsibility.model;

/**
 * @Description UserAccount
 * @Author 张卫刚
 * @Date Created on 2023/7/6
 */
public class UserAccount {
    private String userName;

    private String password;

    private String phoneNumber;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
