package com.zz.juc.multiplethread.z_001;


import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * @Description Atomic vs sync vs LongAdder
 * @Author 张卫刚
 * @Date Created on 2023/7/10
 */
public class T02_AtomicAdvance {

 static AtomicLong count1 = new AtomicLong(0);
  static long count2 = 0;
  static LongAdder count3 = new LongAdder();


    public static void main(String[] args) {
       Thread[] threads = new Thread[1000];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(()->{
                for (int i1 = 0; i1 < 1000; i1++) {
                    count1.incrementAndGet();
                }
            });
        }
        long startTimeCount1 = System.currentTimeMillis();
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("count1 "+ (System.currentTimeMillis()-startTimeCount1));


        Object lock = new Object();
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(()->{
                    for (int j = 0; j < 1000; j++) {
                        synchronized (lock){
                            count2++;
                    }
                }
            });
        }

        long startTimeCount2 = System.currentTimeMillis();
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("count2 "+ (System.currentTimeMillis()-startTimeCount2));


        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(()->{
                for (int i1 = 0; i1 < 1000; i1++) {
                    count3.increment();
                }
            });
        }

        long startTimeCount3 = System.currentTimeMillis();
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("count3 "+ (System.currentTimeMillis()-startTimeCount3));

    }
}
