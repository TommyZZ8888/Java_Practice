package com.zz.juc.concuuent._06_BlockingQueue_start;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @Description _04_OneProducer_MultipleConsumer
 * @Author 张卫刚
 * @Date Created on 2023/6/28
 */
public class _04_OneProducer_MultipleConsumer {
    public static void main(String[] args) {
        int count = 30;
        Queue<String> shaoBingQueue = new LinkedList<>();
        List<String> xiaoBaiQueue = new LinkedList<>();
        List<String> roadPeopleA = new LinkedList<>();
        List<String> roadPeopleB = new LinkedList<>();

        Thread xiaoBaiThread = new Thread(() -> {
            for (int i = 0; i < count; i++) {
                String str = String.format("第%d个烧饼", i + 1);
                shaoBingQueue.add(str);
                xiaoBaiQueue.add(String.format("%d 小白制作了 [%s]", System.currentTimeMillis(), str));
            }
        });

        Thread roadPeopleAThread = new Thread(() -> {
            for (int i = 0; i < count; i++) {
                roadPeopleA.add(String.format("%d 路人甲买到了 [%s]", System.currentTimeMillis(), shaoBingQueue.poll()));
            }
        });

        Thread roadPeopleBThread = new Thread(() -> {
            for (int i = 0; i < count; i++) {
                roadPeopleB.add(String.format("%d 路人乙买到了 [%s]", System.currentTimeMillis(), shaoBingQueue.poll()));
            }
        });

        xiaoBaiThread.start();
        roadPeopleAThread.start();
        roadPeopleBThread.start();

        try {
            xiaoBaiThread.join();
            roadPeopleAThread.join();
            roadPeopleBThread.join();
        } catch (InterruptedException e) {
            System.out.println("interrupt");
        }

        List<String> xiaoBaiMsgSub = xiaoBaiQueue.subList(xiaoBaiQueue.size() - 1, xiaoBaiQueue.size());
        System.out.println(xiaoBaiMsgSub.stream().collect(Collectors.joining("\n")));
        System.out.println("--------------------------");

        System.out.println(String.join("\n", roadPeopleA));
        System.out.println("--------------------------");

        Predicate<String> notContainsNull = str -> !str.contains("null");
        System.out.println(roadPeopleA.stream().filter(notContainsNull).collect(Collectors.joining("\n")));
        System.out.println("--------------------------");
        System.out.println(roadPeopleB.stream().filter(notContainsNull).collect(Collectors.joining("\n")));

    }
}
