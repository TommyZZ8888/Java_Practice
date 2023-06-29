package com.zz.juc.multiplethread.z_000;

/**
 * @Description T04_ThreadState
 * @Author 张卫刚
 * @Date Created on 2023/6/29
 */
public class T04_ThreadState {
    static class MyThread extends Thread{
        @Override
        public void run() {
            System.out.println(this.getState());
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(i);
            }
        }
    }

    public static void main(String[] args) {
        /**
         * NEW
         * RUNNABLE
         * BLOCKED
         * WAITING
         * TIMED_WAITING
         * TERMINATED
         */
        Thread thread = new MyThread();

        System.out.println(thread.getState());

        thread.start();
        System.out.println(thread.getState());
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(thread.getState());
    }
}
