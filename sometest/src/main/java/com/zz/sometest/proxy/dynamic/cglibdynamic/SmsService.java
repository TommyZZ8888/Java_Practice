package com.zz.sometest.proxy.dynamic.cglibdynamic;

/**
 * @Description
 * @Author 张卫刚
 * @Date Created on 2023/4/18
 */
public class SmsService {

    public void sendMessage(String message) {
        System.out.println("smsServiceImpl==>"+message);
    }
}
