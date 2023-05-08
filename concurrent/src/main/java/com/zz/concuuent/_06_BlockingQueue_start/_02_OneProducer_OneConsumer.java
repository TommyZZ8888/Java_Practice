package com.zz.concuuent._06_BlockingQueue_start;

import com.zz.concuuent.utils.SmallTool;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Description _02_OneProducer_OneConsumer
 * @Author 张卫刚
 * @Date Created on 2023/5/8
 */
public class _02_OneProducer_OneConsumer {
    public static void main(String[] args) {
        Queue<String> shaoBingQueue = new LinkedList<>();

        List<String> xiaoBaiMsg = new LinkedList<>();
        List<String> roadPeopleAMsg = new LinkedList<>();

        Thread xiaobai = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                String tmp = String.format("第%d个烧饼", i + 1);
                shaoBingQueue.add(tmp);
                xiaoBaiMsg.add(String.format("%d 小白制作了 [%s]", System.currentTimeMillis(), tmp));
            }
        });

        Thread roadPeopleA = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                roadPeopleAMsg.add(String.format("%d 路人甲买到了 [%s]", System.currentTimeMillis(), shaoBingQueue.poll()));
            }
        });

        xiaobai.start();
        roadPeopleA.start();

        try {
            // 线程join：主线程等待子线程结束
            // 一般情况下，如果子线程需要做一些耗时的操作
            // 主线程要先于子线程结束
            // 如果主线程需要用到子线程的处理结果，主线程等待子线程执行结束
            xiaobai.join();
            roadPeopleA.join();
        } catch (InterruptedException e) {
            SmallTool.printTimeAndThread("appear interrupt");
        }

        System.out.println(String.join("\n", xiaoBaiMsg));
        System.out.println("--------------------");
        System.out.println(String.join("\n", roadPeopleAMsg));
    }
}
