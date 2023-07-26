package com.zz.juc.demos.singleton;

/**
 * @Description LazyManSingleton
 * @Author 张卫刚
 * @Date Created on 2023/7/26
 */
public class LazyManSingleton {

    public LazyManSingleton() {
    }

    public static volatile LazyManSingleton singleton;

    public static LazyManSingleton getSingleton() {
        if (singleton == null) {
            synchronized (LazyManSingleton.class) {
                if (singleton == null) {
                    singleton = new LazyManSingleton();
                }
            }
        }
        return singleton;
    }

    public static void main(String[] args) {
        LazyManSingleton singleton1 = LazyManSingleton.getSingleton();
        LazyManSingleton singleton2 = LazyManSingleton.getSingleton();
        System.out.println(singleton1.hashCode());
        System.out.println(singleton2.hashCode());
    }
}
