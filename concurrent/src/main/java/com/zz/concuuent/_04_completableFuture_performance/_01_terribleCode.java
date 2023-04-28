package com.zz.concuuent._04_completableFuture_performance;

import com.zz.concuuent.utils.Dish;
import com.zz.concuuent.utils.SmallTool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @Description _01_terribleCode
 * @Author 张卫刚
 * @Date Created on 2023/4/28
 */
public class _01_terribleCode {
    public static void main(String[] args) {
        SmallTool.printTimeAndThread("小白和朋友走进餐厅，准备点餐。。。");
        long startTime = System.currentTimeMillis();

        List<Dish> list = new ArrayList<>(10);

        for (int i = 0; i < 10; i++) {
            list.add(new Dish("菜" + i, 1));
        }

        for (Dish dish : list) {
            CompletableFuture.runAsync(dish::make).join();
        }
        SmallTool.printTimeAndThread("ok,所需时间："+ (System.currentTimeMillis()-startTime));
    }
}
