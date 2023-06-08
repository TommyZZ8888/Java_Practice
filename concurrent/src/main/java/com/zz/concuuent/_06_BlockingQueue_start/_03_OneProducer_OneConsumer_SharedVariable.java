package com.zz.concuuent._06_BlockingQueue_start;

import com.zz.concuuent.utils.SmallTool;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Description _03_OneProducer_OneConsumer_SharedVariable
 * @Author 张卫刚
 * @Date Created on 2023/5/9
 */
public class _03_OneProducer_OneConsumer_SharedVariable {
    public static void main(String[] args) {
        final int count = 1200;

        Queue<String> shaoBingQueue = new LinkedList<>();
        List<String> xiaoBaiMsg = new LinkedList<>();
        List<String> roadPeopleAMsg = new LinkedList<>();

        Thread xiaoBaiThread = new Thread(() -> {
            for (int i = 0; i < count; i++) {
                String format = String.format("第%d个烧饼", i + 1);
                shaoBingQueue.offer(format);
                xiaoBaiMsg.add(String.format("%d 小白制作了 %s，当前数量 %d", System.currentTimeMillis(), format, shaoBingQueue.size()));
            }
        });

        Thread roadPeopleAThread = new Thread(() -> {
            for (int i = 0; i < count; i++) {
                roadPeopleAMsg.add(String.format("%d 路人甲买到了 【%s】", System.currentTimeMillis(), shaoBingQueue.poll()));
            }
        });

        xiaoBaiThread.start();
        roadPeopleAThread.start();

        try {
            xiaoBaiThread.join();
            roadPeopleAThread.join();
        } catch (Exception e) {
            SmallTool.printTimeAndThread("appear interrupt");
        }

        List<String> xiaoBaiSubList = xiaoBaiMsg.subList(xiaoBaiMsg.size() - 1, xiaoBaiMsg.size());
        System.out.println(String.join("\n", xiaoBaiSubList));
        System.out.println("============================================");
        List<String> roadPeopleASubList = roadPeopleAMsg.subList(roadPeopleAMsg.size() - 5, roadPeopleAMsg.size());
        System.out.println(String.join("\n", roadPeopleASubList));

    }
}
