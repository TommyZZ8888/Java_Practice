package com.zz.concuuent._01_create_thread;

import com.zz.concuuent.utils.MyThreadFactory;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description _04_CreateThread_ThreadPool
 * @Author 张卫刚
 * @Date Created on 2023/4/27
 */
public class _04_CreateThread_ThreadPool {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 50, 300L, TimeUnit.SECONDS, new LinkedBlockingDeque<>(100), MyThreadFactory.create("test"), new ThreadPoolExecutor.CallerRunsPolicy());

        threadPoolExecutor.execute(()->{
            System.out.println("im child thread");
        });
        System.out.println("main over");
    }
}
