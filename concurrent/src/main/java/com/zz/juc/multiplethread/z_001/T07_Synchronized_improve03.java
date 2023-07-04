package com.zz.juc.multiplethread.z_001;


/**
 * @Description
 * 程序在执行过程中，如果出现异常，默认情况锁会被释放
 * 所以，在并发处理的过程中，有异常要多加小心，不然可能会发生不一致的情况。
 * 比如，在一个web app处理过程中，多个servlet线程共同访问同一个资源，这时如果异常处理不合适，
 * 在第一个线程中抛出异常，其他线程就会进入同步代码区，有可能会访问到异常产生时的数据。
 * 因此要非常小心的处理同步业务逻辑中的异常
 * @Author 张卫刚
 * @Date Created on 2023/6/29
 */
public class T07_Synchronized_improve03 {

    int count = 0;

    synchronized void m() {
        System.out.println(Thread.currentThread().getName() + " start");
        count++;
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (count == 5) {
            int ans;
                ans = 1 / 0;
            System.out.println(ans);
        }
    }

    public static void main(String[] args) {
        T07_Synchronized_improve03 t = new T07_Synchronized_improve03();
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                t.m();
            }
        };
        new Thread(runnable, "r1").start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(runnable, "r2").start();

    }
}
