package com.zz.juc.utils;

/**
 * @Description ErrorExcetion
 * @Author 张卫刚
 * @Date Created on 2023/6/28
 */
public class ErrorException extends RuntimeException {

    private String message;

    public ErrorException(String message) {
        this.message = message;
    }
}
