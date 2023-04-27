package com.zz.concuuent._01_create_thread;

/**
 * @Description _02_CreateThread_Runnable
 * @Author 张卫刚
 * @Date Created on 2023/4/27
 */
public class _02_CreateThread_Runnable {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> System.out.println("im child thread"));
        thread.start();

        System.out.println("main over");


    }
}
