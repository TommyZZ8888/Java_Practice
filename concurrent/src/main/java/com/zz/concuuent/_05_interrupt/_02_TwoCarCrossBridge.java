package com.zz.concuuent._05_interrupt;

import com.zz.concuuent.utils.SmallTool;

import java.util.Random;

/**
 * @Description _02_TwoCarCrossBridge
 * @Author 张卫刚
 * @Date Created on 2023/4/28
 */
public class _02_TwoCarCrossBridge {
    public static void main(String[] args) {

        Thread carTwo = new Thread(() -> {
            SmallTool.printTimeAndThread("car two prepare start cross bridge");
            SmallTool.printTimeAndThread("but car one crossing the bridge");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                SmallTool.printTimeAndThread("car two start cross bridge");
            }
            SmallTool.printTimeAndThread("car two has crossed the bridge");

        });


        Thread carOne = new Thread(() -> {
            SmallTool.printTimeAndThread("car one start cross bridge");
            int timeSleep = new Random().nextInt(500) + 1000;
            SmallTool.sleepMillis(timeSleep);
            SmallTool.printTimeAndThread("car one has crossed the bridge,spend time: " + timeSleep);

            carTwo.interrupt();
        });

        carOne.start();
        carTwo.start();
    }
}
