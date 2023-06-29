package com.zz.juc.multiplethread.z_000;

/**
 * @Description T02_CreateThread
 * @Author 张卫刚
 * @Date Created on 2023/6/29
 */
public class T02_CreateThread {
    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("MyThread");
        }
    }

    static class MyRun implements Runnable {

        @Override
        public void run() {
            System.out.println("MyRun");
        }
    }

    public static void main(String[] args) {
        new MyThread().start();
        new Thread(new MyRun()).start();
        new Thread(() -> System.out.println("Thread")).start();
    }
}
