package com.zz.juc.concuuent._06_BlockingQueue_start;

import com.zz.juc.utils.SmallTool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Description _05_LinkedBlockingQueue_take
 * @Author 张卫刚
 * @Date Created on 2023/6/28
 */
public class _05_LinkedBlockingQueue_take {
    public static void main(String[] args) {
        BlockingQueue<String> queue = new LinkedBlockingQueue<>(3);
        try {
            queue.put("1");
            queue.put("2");
            queue.put("3");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            System.out.println(queue.take());
        } catch (InterruptedException e) {
            SmallTool.printTimeAndThread("取元素被中断");
        }
    }
}
