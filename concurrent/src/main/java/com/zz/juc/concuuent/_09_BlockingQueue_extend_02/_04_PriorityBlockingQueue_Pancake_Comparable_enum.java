package com.zz.juc.concuuent._09_BlockingQueue_extend_02;

import com.zz.juc.utils.SmallTool;

import java.util.Comparator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class _04_PriorityBlockingQueue_Pancake_Comparable_enum {

    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new PriorityBlockingQueue<>(
                3,
                Comparator.comparing(String::strip)
        );

        Thread xiaobai = new Thread(() -> {
            blockingQueue.offer("0");
            SmallTool.printTimeAndThread("做好第1个烧饼");

            blockingQueue.offer("1");
            SmallTool.printTimeAndThread("做好第2个烧饼");

            blockingQueue.offer("2");
            SmallTool.printTimeAndThread("做好第3个烧饼");
        }, "小白");

        xiaobai.start();
        try {
            xiaobai.join();     // 让小白顺利做完 3个烧饼
        } catch (InterruptedException e) {
            SmallTool.printTimeAndThread("被中断" + e.getMessage());
        }

        new Thread(() -> {
            SmallTool.printTimeAndThread("买到烧饼" + blockingQueue.poll());
        }, "张三").start();

    }
}
