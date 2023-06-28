package com.zz.juc.concuuent._04_completableFuture_performance;

import org.w3c.dom.css.CSSPrimitiveValue;

import java.util.concurrent.ForkJoinPool;

/**
 * @Description _04_commonPoolSize
 * @Author 张卫刚
 * @Date Created on 2023/4/28
 */
public class _04_commonPoolSize {
    public static void main(String[] args) {
        // Returns the number of processors available to the Java virtual machine
        System.out.println(Runtime.getRuntime().availableProcessors());
        // 查看 当前线程数
        System.out.println(ForkJoinPool.commonPool().getPoolSize());
        // 查看 最大线程数
        System.out.println(ForkJoinPool.getCommonPoolParallelism());
    }
}
