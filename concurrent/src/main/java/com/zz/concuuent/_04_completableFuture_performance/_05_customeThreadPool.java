package com.zz.concuuent._04_completableFuture_performance;

import com.zz.concuuent.utils.Dish;
import com.zz.concuuent.utils.MyThreadFactory;
import com.zz.concuuent.utils.SmallTool;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @Description _05_customeThreadPool
 * @Author 张卫刚
 * @Date Created on 2023/4/28
 */
public class _05_customeThreadPool {
    public static void main(String[] args) {
        // 执行速度和设置的核心数有关系，
        // 核心数12： 1035ms   核心数5：3030ms
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(12, 50, 300L
                , TimeUnit.SECONDS
                , new LinkedBlockingDeque<>(2000)
                , MyThreadFactory.create("test"));

        SmallTool.printTimeAndThread("小白和朋友走进餐厅，准备点餐。。。");
        long startTime = System.currentTimeMillis();

        CompletableFuture[] futures = IntStream.rangeClosed(1, 12)
                .mapToObj(dish -> new Dish("菜" + dish, 1))
                .map(dish -> CompletableFuture.runAsync(dish::make, threadPoolExecutor))
                .toArray(size -> new CompletableFuture[size]);

        CompletableFuture.allOf(futures).join();
        threadPoolExecutor.shutdown();
        SmallTool.printTimeAndThread("ok,所需时间：" + (System.currentTimeMillis() - startTime));
    }
}
