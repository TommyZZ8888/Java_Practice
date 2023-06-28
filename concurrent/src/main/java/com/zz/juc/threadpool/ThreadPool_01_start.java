package com.zz.juc.threadpool;

import com.zz.juc.utils.ErrorException;
import com.zz.juc.utils.MyThreadFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import org.apache.commons.collections4.ListUtils;

/**
 * @Description ThreadPool_01_start
 * @Author 张卫刚
 * @Date Created on 2023/6/28
 */
public class ThreadPool_01_start {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
        int queueSize = 2000;
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10,
                50,
                300L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(queueSize),
                MyThreadFactory.create("start")
                );

        System.err.println("start: " + System.currentTimeMillis());

        int taskCount = 5000;
        CountDownLatch countDownLatch = new CountDownLatch(taskCount);



        for (int i = 0; i < taskCount; i++) {
            if (threadPoolExecutor.getQueue().size()+10>queueSize) {
                throw new ErrorException("test");
            }
            threadPoolExecutor.execute(() -> {

                System.out.println(Thread.currentThread().getName() + " 测试 " + threadPoolExecutor);
                countDownLatch.countDown();
            });
        }
        try {
            countDownLatch.await();
            System.err.println("end: " + System.currentTimeMillis());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
