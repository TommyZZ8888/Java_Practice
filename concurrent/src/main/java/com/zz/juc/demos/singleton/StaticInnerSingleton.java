package com.zz.juc.demos.singleton;

/**
 * @Description StaticInnerSingleton
 * @Author 张卫刚
 * @Date Created on 2023/7/26
 */
public class StaticInnerSingleton {

    public StaticInnerSingleton() {
    }

    static class InnerClassHolder {
        private static StaticInnerSingleton INSTANCE = new StaticInnerSingleton();
    }


    public static StaticInnerSingleton getInstance() {
        return InnerClassHolder.INSTANCE;
    }


    public static void main(String[] args) {
        StaticInnerSingleton singleton = StaticInnerSingleton.getInstance();
        StaticInnerSingleton singleton1 = StaticInnerSingleton.getInstance();
        System.out.println(singleton1.hashCode());
        System.out.println(singleton.hashCode());
    }

}
