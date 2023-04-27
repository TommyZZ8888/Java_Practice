package com.zz.concuuent.utils;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description MyFactory
 * @Author 张卫刚
 * @Date Created on 2023/4/27
 */
public class MyThreadFactory implements ThreadFactory {

    public static MyThreadFactory create(String namePrefix){
        return new MyThreadFactory(namePrefix);
    }

    private ThreadGroup threadGroup;

    private AtomicInteger threadNum = new AtomicInteger(1);

    private AtomicInteger poolNum = new AtomicInteger(1);

    private final String namePrefix;


    public MyThreadFactory(String namePrefix) {
        this.namePrefix = namePrefix + " pool " + poolNum.getAndIncrement() + "-thread-";
        threadGroup = Thread.currentThread().getThreadGroup();
    }


    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(threadGroup, r, namePrefix + threadNum.getAndIncrement(), 0);
        if (thread.isDaemon()) {
            thread.setDaemon(false);
        }
        if (thread.getPriority() != Thread.NORM_PRIORITY) {
            thread.setPriority(Thread.NORM_PRIORITY);
        }
        return thread;
    }
}
