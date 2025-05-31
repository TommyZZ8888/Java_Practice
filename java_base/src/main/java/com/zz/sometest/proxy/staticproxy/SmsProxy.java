package com.zz.sometest.proxy.staticproxy;

/**
 * @Description
 * @Author 张卫刚
 * @Date Created on 2023/4/18
 */
public class SmsProxy implements SmsService{

    private SmsService smsService;

    public SmsProxy(SmsService smsService){
        this.smsService = smsService;
    }

    @Override
    public void sendMessage(String message) {
        smsService.sendMessage(message);
    }
}
