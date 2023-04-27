package com.zz.concuuent._01_create_thread;

import com.zz.concuuent.utils.SmallTool;

/**
 * @Description _01_CreateThread_run
 * @Author 张卫刚
 * @Date Created on 2023/4/27
 */
public class _01_CreateThread_run {
    public static void main(String[] args) {


        Thread thread = new Thread(){
            @Override
            public void run() {
                System.out.println("im child thread");
            }
        };

        thread.start();
        System.out.println("main thread over");
    }
}
