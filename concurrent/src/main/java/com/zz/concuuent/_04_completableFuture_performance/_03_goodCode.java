package com.zz.concuuent._04_completableFuture_performance;

import com.zz.concuuent.utils.Dish;
import com.zz.concuuent.utils.SmallTool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

/**
 * @Description _03_goodCode
 * @Author 张卫刚
 * @Date Created on 2023/4/28
 */
public class _03_goodCode {
    public static void main(String[] args) {
        //-Djava.util.concurrent.ForkJoinPool.common.parallelism=8
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "12");

        SmallTool.printTimeAndThread("小白和朋友走进餐厅，准备点餐。。。");
        long startTime = System.currentTimeMillis();

        CompletableFuture[] futures = IntStream.rangeClosed(1, 12)
                .mapToObj(dish -> new Dish("菜" + dish, 1))
                .map(dish -> CompletableFuture.runAsync(dish::make))
                .toArray(CompletableFuture[]::new);

        CompletableFuture.allOf(futures).join();

        SmallTool.printTimeAndThread("ok,所需时间：" + (System.currentTimeMillis() - startTime));
    }
}
