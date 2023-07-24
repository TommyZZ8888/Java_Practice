package com.zz.sometest.proxy.staticproxy;

/**
 * @Description
 * @Author 张卫刚
 * @Date Created on 2023/4/18
 */
public class SmsServiceImpl implements SmsService{
    @Override
    public void sendMessage(String message) {
        System.out.println("smsServiceImpl==>"+message);
    }
}
