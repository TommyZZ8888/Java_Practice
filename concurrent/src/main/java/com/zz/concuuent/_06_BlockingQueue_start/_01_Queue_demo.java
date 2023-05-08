package com.zz.concuuent._06_BlockingQueue_start;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description _01_Queue_demo
 * @Author 张卫刚
 * @Date Created on 2023/5/8
 */
public class _01_Queue_demo {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();
        queue.offer("one");
        queue.offer("two");
        queue.offer("three");

        System.out.println("start print==========");
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println("over print===========");
    }
}
