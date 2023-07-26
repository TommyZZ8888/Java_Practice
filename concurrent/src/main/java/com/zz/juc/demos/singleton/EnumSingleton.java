package com.zz.juc.demos.singleton;

/**
 * @Description EnumSingleton
 * @Author 张卫刚
 * @Date Created on 2023/7/26
 */
public class EnumSingleton {

    public EnumSingleton(){}

    public static void main(String[] args) {
        SingletonEnum instance1 = SingletonEnum.INSTANCE;
        SingletonEnum instance2 = SingletonEnum.INSTANCE;
        System.out.println(instance2.hashCode());
        System.out.println(instance1.hashCode());
    }
}

enum SingletonEnum{
    INSTANCE;
}
