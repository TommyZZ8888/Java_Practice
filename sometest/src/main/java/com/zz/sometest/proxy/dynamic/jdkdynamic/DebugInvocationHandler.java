package com.zz.sometest.proxy.dynamic.jdkdynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Description
 * @Author 张卫刚
 * @Date Created on 2023/4/18
 */
public class DebugInvocationHandler implements InvocationHandler {


    private final Object target;

    public DebugInvocationHandler(Object target){
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(target, args);
    }
}
