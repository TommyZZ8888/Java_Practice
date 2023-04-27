package com.zz.concuuent.utils;


import java.util.StringJoiner;

/**
 * @Description SmallTool
 * @Author 张卫刚
 * @Date Created on 2023/4/27
 */
public class SmallTool {


    public static void sleepMillis(long mills){
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void printTimeAndThread(String tag){
        String str = new StringJoiner("\t|\t")
                .add(String.valueOf(System.currentTimeMillis()))
                .add(String.valueOf(Thread.currentThread().getId()))
                .add(Thread.currentThread().getName())
                .add(tag)
                .toString();
        System.out.println(str);
    }
}
