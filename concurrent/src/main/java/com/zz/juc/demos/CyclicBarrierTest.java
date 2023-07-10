package com.zz.juc.demos;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Description barrierAction 在屏障位置不同时 执行顺序
 * @Author 张卫刚
 * @Date Created on 2023/7/10
 */
public class CyclicBarrierTest {
    static CyclicBarrier cyclicBarrier = new CyclicBarrier(2, new A());

    public static void main(String[] args) {
        afterAwait();
    }

    static void beforeAwait() {
        for (int i = 0; i < 2; i++) {
            int finalI = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()
                        + "收集" + finalI + "个龙珠");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }
    }

    static void afterAwait() {
        new Thread(() -> {
            try {
                cyclicBarrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(1);

        }).start();

        try {
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
        System.out.println(2);
    }


    static class A implements Runnable {

        @Override
        public void run() {
            System.out.println(3);
        }
    }
}
