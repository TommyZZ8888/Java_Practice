package com.zz.concuuent._05_interrupt;

import com.zz.concuuent.utils.SmallTool;

/**
 * @Description _04_BeforehandInterrupt
 * @Author 张卫刚
 * @Date Created on 2023/5/6
 */
public class _04_BeforehandInterrupt {
    public static void main(String[] args) {
        Thread.currentThread().interrupt();

        try {
            SmallTool.printTimeAndThread("start sleep");
            Thread.sleep(100);
        } catch (InterruptedException e) {
            SmallTool.printTimeAndThread("appear interrupt");
        }
        SmallTool.printTimeAndThread("wakeup");
    }
}
