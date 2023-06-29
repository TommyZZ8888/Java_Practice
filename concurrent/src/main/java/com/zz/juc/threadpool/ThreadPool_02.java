package com.zz.juc.threadpool;

import com.google.common.collect.Lists;
import com.zz.juc.utils.ErrorException;
import com.zz.juc.utils.MyThreadFactory;

import java.util.List;
import java.util.concurrent.*;

/**
 * @Description ThreadPool_01_start
 * @Author 张卫刚
 * @Date Created on 2023/6/28
 */
public class ThreadPool_02 {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
        int queueSize = 2000;
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                10,// 核心线程池大小(候客区窗口10个)
                50,           // 最大核心线程池大小(总共50个窗口)
                300L, TimeUnit.SECONDS,// 超时3秒没有人调用就会释，放关闭窗口
                new LinkedBlockingQueue<>(queueSize),// 阻塞队列(候客区最多2000人)
                MyThreadFactory.create("start")
        );

        List<Callable<Void>> callables = Lists.newArrayListWithCapacity(3);
        callables.add(() -> {
            System.out.println("1");
            return null;
        });

        callables.add(()->{
            System.out.println("2");
            return null;
        });

        callables.add(()->{
            System.out.println("3");
            return null;
        });

        try {
            threadPoolExecutor.invokeAll(callables);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
