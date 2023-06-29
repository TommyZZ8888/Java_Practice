package com.zz.juc.multiplethread.z_000;

/**
 * @Description T03_Sleep_Yield_Join
 * @Author 张卫刚
 * @Date Created on 2023/6/29
 */
public class T03_Sleep_Yield_Join {
    public static void main(String[] args) {
//        sleep();
//        yield();
        join();
    }

    static void sleep() {
        new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                System.out.println("A" + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    static void yield() {
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                System.out.println("A" + i);
                if (i % 5 == 0) {
                    Thread.yield();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                System.out.println("B" + i);
                if (i % 5 == 0) {
                    Thread.yield();
                }
            }
        }).start();
    }

    static void join(){
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                System.out.println("A" + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread thread2 = new Thread(() -> {

            try {
                thread1.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            for (int i = 0; i < 20; i++) {
                System.out.println("B" + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}
