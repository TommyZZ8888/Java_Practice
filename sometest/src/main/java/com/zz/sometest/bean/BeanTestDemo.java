package com.zz.sometest.bean;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Description BeanTestDemo
 * @Author 张卫刚
 * @Date Created on 2023/6/8
 */
public class BeanTestDemo {

    @Autowired
    public List<BaseService> baseServiceList;


}
