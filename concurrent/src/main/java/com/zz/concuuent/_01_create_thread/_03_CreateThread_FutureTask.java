package com.zz.concuuent._01_create_thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @Description _03_CreateThread_FutureTask
 * @Author 张卫刚
 * @Date Created on 2023/4/27
 */
public class _03_CreateThread_FutureTask {
    public static void main(String[] args) {

        Callable<String> callable = () -> {
            System.out.println("im child thread");
            return "sub task done";
        };

        FutureTask<String> stringFutureTask = new FutureTask<>(callable);

        Thread thread = new Thread(stringFutureTask);
        thread.start();
        System.out.println("child thread start");

        System.out.println("main over");

    }
}


