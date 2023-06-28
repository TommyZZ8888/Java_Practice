package com.zz.juc.concuuent._08_BlockingQueue_extend_01;

import com.zz.juc.utils.SmallTool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Description _01_LinkedBlocking_unfair
 * @Author 张卫刚
 * @Date Created on 2023/6/28
 */
public class _01_LinkedBlocking_unfair {
    public static void main(String[] args) {
        BlockingQueue<String> shaobingQueue = new LinkedBlockingQueue<>(3);

        new Thread(() -> {
            try {
                SmallTool.printTimeAndThread("来买烧饼");
                String shaobing = shaobingQueue.poll(1, TimeUnit.SECONDS);
                String tag = shaobing == null ? "再见, 以后不来了" : "买到烧饼了";
                SmallTool.printTimeAndThread(tag);
            } catch (InterruptedException e) {
                SmallTool.printTimeAndThread("被中断" + e.getMessage());
            }
        }, "张三").start();

        SmallTool.sleepMillis(100);     // 模拟张三先到

        new Thread(() -> {
            try {
                SmallTool.printTimeAndThread("来买烧饼");
                String shaobing = shaobingQueue.poll(1, TimeUnit.SECONDS);
                String tag = shaobing == null ? "草, 掀摊子! " : "买到烧饼了";
                SmallTool.printTimeAndThread(tag);
            } catch (InterruptedException e) {
                SmallTool.printTimeAndThread("被中断" + e.getMessage());
            }
        }, "李四").start();

        shaobingQueue.offer("芝麻烧饼");
    }
}
