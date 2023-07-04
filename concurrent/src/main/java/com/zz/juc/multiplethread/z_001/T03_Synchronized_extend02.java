package com.zz.juc.multiplethread.z_001;

/**
 * @Description MyThread
 * @Author 张卫刚
 * @Date Created on 2023/6/29
 */
public class T03_Synchronized_extend02 {
private volatile int count = 10;

    public synchronized void run() {
        System.out.println(Thread.currentThread().getName() + " m1 start...");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " m1 end");
    }

    public void run2(){
        System.out.println(Thread.currentThread().getName() + " m2 start...");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " m2 end");
    }

    public static void main(String[] args) {
        T03_Synchronized_extend02 thread = new T03_Synchronized_extend02();
        new Thread(thread::run,"t1").start();
        new Thread(thread::run2,"t2").start();

    }
}
