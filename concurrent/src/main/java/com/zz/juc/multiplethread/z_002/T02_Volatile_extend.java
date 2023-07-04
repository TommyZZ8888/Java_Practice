package com.zz.juc.multiplethread.z_002;

import java.util.concurrent.TimeUnit;

/**
 * @Description Volatile引用类型（包含数组）只能保证引用本身的可见性，不能保证内部字段的可见性
 * @Author 张卫刚
 * @Date Created on 2023/7/4
 */
public class T02_Volatile_extend {

    boolean flag = true;

    volatile static T02_Volatile_extend t = new T02_Volatile_extend();

    void m() {
        System.out.println("m start");

        while (flag) {

        }
        System.out.println("m end");
    }

    public static void main(String[] args) {
        new Thread(t::m, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.flag = false;
    }

}
