package com.zz.juc.concuuent._07_BlockingQueue_basic;


import com.zz.juc.utils.SmallTool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class _04_BlockingQueue_poll_0 {
    public static void main(String[] args) {
        BlockingQueue<String> shaobingQueue = new LinkedBlockingQueue<>(3);

        Thread xiaoBai = new Thread(() -> {
            SmallTool.printTimeAndThread("小白 收拾东西，准备开张");
            SmallTool.printTimeAndThread("小白 接到电话 往家里跑");
        });

        Thread roadPeopleA = new Thread(() -> {
            SmallTool.printTimeAndThread("路人甲 来买烧饼");
            String shaobing = shaobingQueue.poll();
            if (shaobing == null) {
                SmallTool.printTimeAndThread("路人甲 没有买到烧饼");
            } else {
                SmallTool.printTimeAndThread("路人甲 买到了烧饼: " + shaobing);
            }
        });

        xiaoBai.start();
        try {
            Thread.sleep(10);   // 先等小白收拾一下，再让路人甲出场
        } catch (InterruptedException e) {
            SmallTool.printTimeAndThread("主线程 被中断" + e.getMessage());
        }
        roadPeopleA.start();
    }
}
