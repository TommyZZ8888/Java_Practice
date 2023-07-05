package com.zz.juc.multiplethread;

import java.util.*;
import java.util.concurrent.Callable;

import com.google.common.collect.Lists;

/**
 * @Description ThreadDemo01
 * @Author 张卫刚
 * @Date Created on 2023/6/29
 */
public class ThreadDemo01 {
    public static void main(String[] args) {
        System.out.println(new ThreadDemo01().synchronizedTest());

    }

    public Boolean synchronizedTest(){
        System.out.println(this.toString());
        System.out.println(ThreadDemo01.class.toString());
       return this.equals(ThreadDemo01.class);
    }
}
