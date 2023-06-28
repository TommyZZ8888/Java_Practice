package com.zz.juc.concuuent._06_BlockingQueue_start;

import com.zz.juc.utils.SmallTool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Description _06_LinkedBlockingQueue_put
 * @Author 张卫刚
 * @Date Created on 2023/6/28
 */
public class _06_LinkedBlockingQueue_put {
    public static void main(String[] args) {
        BlockingQueue<String> queue = new LinkedBlockingQueue<>(1);

        try {
            queue.put("1");
            SmallTool.printTimeAndThread("1 in");

            queue.put("2");
            SmallTool.printTimeAndThread("2 in");
        } catch (InterruptedException e) {
            SmallTool.printTimeAndThread("interrupt");
        }
    }
}
