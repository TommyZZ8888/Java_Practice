package com.ttt.springframe.config;

/**
 * @Description ApplicationContextAware
 * @Author 张卫刚
 * @Date Created on 2023/7/24
 */
public interface ApplicationContextAware {

    /**
     * setApplicationAware
     * @param applicationContext
     */
    void setApplicationContext(DogApplicationContext applicationContext);
}
