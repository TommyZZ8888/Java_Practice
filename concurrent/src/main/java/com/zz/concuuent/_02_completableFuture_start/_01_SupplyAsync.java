package com.zz.concuuent._02_completableFuture_start;

import com.zz.concuuent.utils.SmallTool;

import java.util.concurrent.CompletableFuture;

/**
 * @Description _01_SupplyAsync
 * @Author 张卫刚
 * @Date Created on 2023/4/27
 */
public class _01_SupplyAsync {
    public static void main(String[] args) {
        SmallTool.printTimeAndThread("小白进入餐厅");
        SmallTool.printTimeAndThread("小白点了 番茄炒蛋 + 一碗米饭");

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            SmallTool.printTimeAndThread("厨师炒菜");
            SmallTool.sleepMillis(200);
            SmallTool.printTimeAndThread("厨师打饭");
            SmallTool.sleepMillis(100);
            return "番茄炒蛋 ok";
        });
        String join = completableFuture.join();
        SmallTool.printTimeAndThread("小白在打王者");
        SmallTool.printTimeAndThread(String.format("%s,小白开吃", join));
    }
}

