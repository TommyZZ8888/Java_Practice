package com.zz.juc.multiplethread.z_000;

import java.util.concurrent.TimeUnit;

/**
 * @Description T01_WhatIsThread
 * @Author 张卫刚
 * @Date Created on 2023/6/29
 */
public class T01_WhatIsThread {
    private static class T1 extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    TimeUnit.MICROSECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("T1");
        }
    }


    public static void main(String[] args) {
        new T1().start();
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.MICROSECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("main");
    }
}
