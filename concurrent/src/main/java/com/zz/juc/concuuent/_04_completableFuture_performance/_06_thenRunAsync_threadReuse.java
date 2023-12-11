package com.zz.juc.concuuent._04_completableFuture_performance;

import com.zz.juc.utils.SmallTool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description _06_thenRunAsync_threadReuse
 * @Author 张卫刚
 * @Date Created on 2023/4/28
 */
public class _06_thenRunAsync_threadReuse {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                0, TimeUnit.MILLISECONDS,
                new SynchronousQueue<Runnable>());

//        CompletableFuture.runAsync(() -> {
//                    try {
//                        Thread.sleep(3000);
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
//                    SmallTool.printTimeAndThread("A");
//                }, executor)
//                .thenRunAsync(() -> SmallTool.printTimeAndThread("B"), executor)
//                .join();
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("a");
            return "a";
        }, executor);
        CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(() -> {

            System.out.println("b");
            return "a";
        }, executor);
        CompletableFuture<String> completableFuture3 = CompletableFuture.supplyAsync(() -> {

            System.out.println("c");
            return "a";
        }, executor);

        List<CompletableFuture<String>> list = new ArrayList<>();
        list.add(completableFuture);
        list.add(completableFuture2);
        list.add(completableFuture3);
        CompletableFuture.allOf(list.toArray(new CompletableFuture[0])).join();
    }
}
