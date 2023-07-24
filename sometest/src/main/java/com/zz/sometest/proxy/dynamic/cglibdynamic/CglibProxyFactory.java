package com.zz.sometest.proxy.dynamic.cglibdynamic;

import org.springframework.cglib.proxy.Enhancer;

/**
 * @Description
 * @Author 张卫刚
 * @Date Created on 2023/4/18
 */
public class CglibProxyFactory {

    public static Object getProxy(Class<?> clz) {
        //动态代理增强类
        Enhancer enhancer = new Enhancer();
        //设置加载器
        enhancer.setClassLoader(clz.getClassLoader());
        //设置被代理类
        enhancer.setSuperclass(clz);
        //设置方法拦截器
        enhancer.setCallback(new DebugMethodInterceptor());
        //创建代理类
        return enhancer.create();
    }
}
