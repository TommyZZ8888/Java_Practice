package com.zz.sometest.proxy.dynamic.jdkdynamic;

import java.lang.reflect.Proxy;

/**
 * @Description
 * @Author 张卫刚
 * @Date Created on 2023/4/18
 */
public class JDKProxyFactory {

    public static Object getProxy(Object proxy){
        return Proxy.newProxyInstance(
                proxy.getClass().getClassLoader(),
                proxy.getClass().getInterfaces(),
                new DebugInvocationHandler(proxy)
        );
    }
}
