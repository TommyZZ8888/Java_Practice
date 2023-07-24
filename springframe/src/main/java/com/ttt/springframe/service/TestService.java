package com.ttt.springframe.service;

import com.ttt.springframe.config.ApplicationContextAware;
import com.ttt.springframe.config.BeanNameAware;
import com.ttt.springframe.config.DogApplicationContext;
import com.ttt.springframe.config.AutoWired;
import com.ttt.springframe.config.Component;

/**
 * @Description TestService
 * @Author 张卫刚
 * @Date Created on 2023/7/24
 */

@Component
public class TestService implements ApplicationContextAware, BeanNameAware {

    @AutoWired
    private ServiceDemo serviceDemo;

    private String beanName;
    private DogApplicationContext applicationContext;

    public void test() {
        serviceDemo.say();
        System.out.println("testService:" + applicationContext.getBean("testService"));
        System.out.println("beanName:" + beanName);
    }


    @Override
    public void setApplicationContext(DogApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }
}
