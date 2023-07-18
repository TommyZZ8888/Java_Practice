package com.zz.juc.multiplethread.z_000;


/**
 * @Description
 * 一个同步方法可以调用另一个同步方法，一个线程已经拿到对象的锁，再次申请仍会拿到该对象的锁
 * 也就是说synchronized的锁是可重入的
 * @Author 张卫刚
 * @Date Created on 2023/6/29
 */
public class T09_Synchronized_improve {

    synchronized void m1() {
        System.out.println("m1 start");
        try {
            Thread.sleep(100);
        } catch (Exception e) {
            e.printStackTrace();
        }
        m2();
        System.out.println(Thread.currentThread().getName());
        System.out.println("m1 end");
    }

    synchronized void m2() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
        System.out.println("m2");
    }

    public static void main(String[] args) {
        new T09_Synchronized_improve().m1();
    }
}
