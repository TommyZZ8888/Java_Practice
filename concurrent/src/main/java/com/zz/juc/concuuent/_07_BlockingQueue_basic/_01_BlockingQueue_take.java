package com.zz.juc.concuuent._07_BlockingQueue_basic;

import com.zz.juc.utils.SmallTool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Description _01_BlockingQueue_take
 * @Author 张卫刚
 * @Date Created on 2023/6/28
 */
public class _01_BlockingQueue_take {
    public static void main(String[] args) {
        BlockingQueue<String> queue = new LinkedBlockingQueue<>(3);

        Thread xiaoBaiThread = new Thread(() -> {
            SmallTool.printTimeAndThread("小白 收拾东西，准备开张");
            SmallTool.printTimeAndThread("小白 接到电话 往家里跑");
        });

        Thread roadAThread = new Thread(() -> {
            SmallTool.printTimeAndThread("路人甲 来买烧饼");
            try {
                String shaobing = queue.take();
                SmallTool.printTimeAndThread("路人甲 买到了烧饼: " + shaobing);
            } catch (InterruptedException e) {
                SmallTool.printTimeAndThread("路人甲 被中断" + e.getMessage());
            }
        });

        xiaoBaiThread.start();
        try {
            Thread.sleep(1000);   // 先等小白收拾一下，再让路人甲出场
        } catch (InterruptedException e) {
            SmallTool.printTimeAndThread("主线程 被中断" + e.getMessage());
        }
        roadAThread.start();
    }
}
