package com.zz.juc.threadpool;

import com.zz.juc.utils.ErrorException;
import com.zz.juc.utils.MyThreadFactory;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description ThreadPool_01_start
 * @Author 张卫刚
 * @Date Created on 2023/6/28
 */
public class ThreadPool_03 {
    public static void main(String[] args) {
        int queueSize = 2000;
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                10,
                50,
                300L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(queueSize),
                MyThreadFactory.create("start")
        );

        long startTime = System.currentTimeMillis();
        int taskCount = 2000;
        CountDownLatch countDownLatch = new CountDownLatch(taskCount);

        for (int i = 0; i < taskCount; i++) {
            if (threadPoolExecutor.getQueue().size() + threadPoolExecutor.getMaximumPoolSize() > queueSize) {
                throw new ErrorException("阻塞队列满了");
            }
            Future<String> submit = threadPoolExecutor.submit(() -> {
                countDownLatch.countDown();
                return "ok";
            });
            try {
                String s = submit.get();
                System.out.println("==================" + s);
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            countDownLatch.await();
            long endTime = System.currentTimeMillis();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
