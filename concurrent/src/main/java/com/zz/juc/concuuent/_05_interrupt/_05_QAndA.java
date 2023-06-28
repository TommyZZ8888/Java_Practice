package com.zz.juc.concuuent._05_interrupt;

import com.zz.juc.utils.SmallTool;

import java.util.concurrent.TimeUnit;

/**
 * @Description _05_QAndA
 * @Author 张卫刚
 * @Date Created on 2023/5/8
 */
public class _05_QAndA {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            SmallTool.printTimeAndThread("start sleep");
            forceSleep(3);
            SmallTool.printTimeAndThread("over sleep");
        });
        thread.start();
        thread.interrupt();
    }

    @SuppressWarnings("BusyWait")
    public static void forceSleep(int second) {
        long startTime = System.currentTimeMillis();
        long sleepMillis = TimeUnit.SECONDS.toMillis(second);

        while ((startTime + sleepMillis) > System.currentTimeMillis()) {
            long sleepTime = startTime + sleepMillis - System.currentTimeMillis();
            if (sleepTime < 0) {
                break;
            }
            try {
                Thread.sleep(sleepMillis);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}

