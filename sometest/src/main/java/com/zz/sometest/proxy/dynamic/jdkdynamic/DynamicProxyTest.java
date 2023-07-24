package com.zz.sometest.proxy.dynamic.jdkdynamic;

/**
 * @Description
 * @Author 张卫刚
 * @Date Created on 2023/4/18
 */
public class DynamicProxyTest {
    public static void main(String[] args) {
        SmsService proxy = (SmsService) JDKProxyFactory.getProxy(new SmsServiceImpl());
        proxy.sendMessage("message");
    }
}
