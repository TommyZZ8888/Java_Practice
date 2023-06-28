package com.zz.juc.concuuent._05_interrupt;

/**
 * @Description _01_ThreadState
 * @Author 张卫刚
 * @Date Created on 2023/4/28
 */
public class _01_ThreadState {
    public static void main(String[] args) {
        Thread thread = new Thread();
        System.out.println(thread.getState());

        thread.start();
        System.out.println(thread.getState());

        try {
            Thread.sleep(100);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(thread.getState());
    }
}
