package com.zz.juc.concuuent._03_completableFuture_advance;

import com.zz.juc.utils.SmallTool;

import java.util.concurrent.CompletableFuture;

/**
 * @Description _03_exceptionally
 * @Author 张卫刚
 * @Date Created on 2023/4/28
 */
public class _03_exceptionally {
    public static void main(String[] args) {
        SmallTool.printTimeAndThread("张三走出餐厅，来到公交站");
        SmallTool.printTimeAndThread("等待 700路 或者 800路 公交到来");

        CompletableFuture<String> bus = CompletableFuture.supplyAsync(() -> {
            SmallTool.printTimeAndThread("700路公交正在赶来");
            SmallTool.sleepMillis(100);
            return "700路到了";
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            SmallTool.printTimeAndThread("800路公交正在赶来");
            SmallTool.sleepMillis(200);
            return "800路到了";
        }), firstComeBus -> {
            SmallTool.printTimeAndThread(firstComeBus);
            if (firstComeBus.startsWith("700")) {
                throw new RuntimeException("撞树了。。。");
            }
            return firstComeBus;
        }).exceptionally(e -> {
            SmallTool.printTimeAndThread(e.getMessage());
            SmallTool.printTimeAndThread("小白叫到出租车");
            SmallTool.sleepMillis(200);
            return "叫到出租车";
        });
        SmallTool.printTimeAndThread(String.format("%s,小白坐车回家", bus.join()));
    }
}