package com.zz.juc.concuuent._02_completableFuture_start;

import com.zz.juc.utils.SmallTool;

import java.util.concurrent.CompletableFuture;

/**
 * @Description _02_ThenCompose
 * @Author 张卫刚
 * @Date Created on 2023/4/27
 */
public class _02_ThenCompose {
    public static void main(String[] args) {

        SmallTool.printTimeAndThread("小白进入餐厅");
        SmallTool.printTimeAndThread("小白点了 番茄炒蛋 + 一碗米饭");

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            SmallTool.printTimeAndThread("厨师炒菜");
            SmallTool.sleepMillis(200);

            return "番茄炒蛋 ok";
        }).thenCompose(dish -> CompletableFuture.supplyAsync(() -> {
            SmallTool.printTimeAndThread("厨师打饭");
            SmallTool.sleepMillis(100);
            return dish + "米饭";
        }));

        String join = completableFuture.join();
        SmallTool.printTimeAndThread("小白在打王者");
        SmallTool.printTimeAndThread(String.format("%s,小白开吃", join));


    }


    public static void supplyAsyncTest() {
        SmallTool.printTimeAndThread("小白进入餐厅");
        SmallTool.printTimeAndThread("小白点了 番茄炒蛋 + 一碗米饭");

        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            SmallTool.printTimeAndThread("厨师炒菜");
            SmallTool.sleepMillis(200);
            CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> {
                SmallTool.printTimeAndThread("服务员打饭");
                SmallTool.sleepMillis(100);
                return " +米饭";
            });

            return "番茄炒蛋" + cf2.join() + "ok";
        });
        String join = cf1.join();
        SmallTool.printTimeAndThread("小白在打王者");
        SmallTool.printTimeAndThread(String.format("%s,小白开吃", join));
    }
}
