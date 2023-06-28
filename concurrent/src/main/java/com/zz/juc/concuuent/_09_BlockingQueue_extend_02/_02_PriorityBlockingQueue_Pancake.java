package com.zz.juc.concuuent._09_BlockingQueue_extend_02;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class _02_PriorityBlockingQueue_Pancake {

    public static void main(String[] args) {
        BlockingQueue<Integer> blockingQueue = new PriorityBlockingQueue<>();

        blockingQueue.offer(1);
    }
}
