package com.zz.juc.multiplethread.z_000;

import java.util.concurrent.TimeUnit;

/**
 * @Description MyThread
 * @Author 张卫刚
 * @Date Created on 2023/6/29
 */
public class T08_Synchronized_extend03 {
    String name;
    double balance;

    public synchronized void set(String name, double balance) {
        this.name = name;

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        this.balance = balance;
    }

    public /*synchronized*/ double getBalance(String name) {
        return this.balance;
    }


    public static void main(String[] args) {
        T08_Synchronized_extend03 a = new T08_Synchronized_extend03();
        new Thread(()->a.set("zhangsan", 100.0)).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(a.getBalance("zhangsan"));

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(a.getBalance("zhangsan"));
    }
}
