package com.zz.sometest.proxy.dynamic.cglibdynamic;

/**
 * @Description
 * @Author 张卫刚
 * @Date Created on 2023/4/18
 */
public class CglibProxyTest {
    public static void main(String[] args) {
        WeChatMessage proxy = (WeChatMessage) CglibProxyFactory.getProxy(WeChatMessage.class);
        proxy.sendMessage("message");

    }
}
