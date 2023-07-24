package com.ttt.springframe.service;

import com.ttt.springframe.config.Transaction;
import com.ttt.springframe.config.Component;

/**
 * @Description 类的作用说明
 * @Author 张卫刚
 * @Date Created on 2023/7/24
 */
@Component
@Transaction
public class ServiceDemo {

    public void say(){
        System.out.println("hello");
    }
}
