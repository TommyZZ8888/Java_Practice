package com.zz.juc.multiplethread;

import cn.hutool.core.date.DateUtil;

import java.util.Date;

/**
 * @Description ThreadDemo01
 * @Author 张卫刚
 * @Date Created on 2023/6/29
 */
public class ThreadTest {
    public static void main(String[] args) {
        System.out.println((DateUtil.offsetDay(new Date(), 1).getTime() / 1000) - DateUtil.currentSeconds());

    }

    public Boolean synchronizedTest(){
        System.out.println(this);
        System.out.println(ThreadTest.class);
       return this.equals(ThreadTest.class);
    }
}
