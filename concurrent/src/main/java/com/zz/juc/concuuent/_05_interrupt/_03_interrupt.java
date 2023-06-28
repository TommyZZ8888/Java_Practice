package com.zz.juc.concuuent._05_interrupt;

import com.zz.juc.utils.SmallTool;

/**
 * @Description _03_interrupt
 * @Author 张卫刚
 * @Date Created on 2023/4/28
 */
public class _03_interrupt {
    public static void main(String[] args) {
        Thread carOne = new Thread(() -> {
            long startMillis = System.currentTimeMillis();
            while ((System.currentTimeMillis() - startMillis) < 3) {
                if (Thread.interrupted()) {
                    System.out.println("left");
                } else {
                    System.out.println("right");
                }
            }
        });

        carOne.start();

        SmallTool.sleepMillis(1);
        carOne.interrupt();

    }
}
