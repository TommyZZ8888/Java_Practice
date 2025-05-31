package com.zz.sometest.proxy.staticproxy;

/**
 * @Description
 * @Author 张卫刚
 * @Date Created on 2023/4/18
 */
public class ProxyTest {
    public static void main(String[] args) {
        WeChatService smsService = new WeChatService();
        SmsProxy smsProxy = new SmsProxy(smsService);
        smsProxy.sendMessage("message");
    }
}
