package com.zz.sometest.proxy.dynamic.cglibdynamic;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Description
 * @Author 张卫刚
 * @Date Created on 2023/4/18
 */
public class DebugMethodInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Object invoke = methodProxy.invokeSuper(o, objects);
        return invoke;
    }
}
