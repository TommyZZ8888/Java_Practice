package com.zz.juc.multiplethread.z_001;

/**
 * @Description T01_Volatile_start
 * @Author 张卫刚
 * @Date Created on 2023/7/4
 */
public class T08_Volatile_start {

    //有无volatile测试
   /* volatile */ boolean flag = false;

    void m() {
        System.out.println("m start");
        flag = true;

        while (flag) {
        }
        System.out.println("m end");
    }

    public static void main(String[] args) {
        T08_Volatile_start t = new T08_Volatile_start();
//        Runnable runnable = new Runnable() {
//
//            @Override
//            public void run() {
//                t.m();
//            }
//        };
        new Thread(t::m, "m1").start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.flag = false;
    }
}
