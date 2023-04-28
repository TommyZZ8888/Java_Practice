package com.zz.concuuent._03_completableFuture_advance;

import com.zz.concuuent.utils.SmallTool;

import java.util.concurrent.CompletableFuture;

/**
 * @Description _02_applyToEither
 * @Author 张卫刚
 * @Date Created on 2023/4/28
 */
public class _02_applyToEither {
    public static void main(String[] args) {

        SmallTool.printTimeAndThread("张三走出餐厅，来到公交站");
        SmallTool.printTimeAndThread("等待 700路 或者 800路 公交到来");

        CompletableFuture<String> bus = CompletableFuture.supplyAsync(() -> {
            SmallTool.printTimeAndThread("700路公交正在赶来");
            SmallTool.sleepMillis(200);
            return "700路到了";
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            SmallTool.printTimeAndThread("800路公交正在赶来");
            SmallTool.sleepMillis(200);
            return "800路到了";
        }),firstBusCome->firstBusCome);

        SmallTool.printTimeAndThread(String.format("%s,小白坐车回家", bus.join()));
    }
}
