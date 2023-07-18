package com.zz.juc.multiplethread.z_000;

/**
 * @Description MyThread
 * @Author 张卫刚
 * @Date Created on 2023/6/29
 */
public class T06_Synchronized_extend implements Runnable{
private volatile int count = 10;

    @Override
    public /**synchronized*/ void run() {
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    public static void main(String[] args) {
        T06_Synchronized_extend myThread = new T06_Synchronized_extend();
        for (int i = 0; i < 10; i++) {
            new Thread(myThread,"Thread"+i).start();
        }
    }
}
