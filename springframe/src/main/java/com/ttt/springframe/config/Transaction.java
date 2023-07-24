package com.ttt.springframe.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description Transaction
 * SOURCE 只在源代码中存在，编译时忽略
 * CLASS 编译时保留，不会加载到JVM运行时内存
 * RUNTIME 在运行时保留，可以通过反射机制获取注解信息
 * @Author 张卫刚
 * @Date Created on 2023/7/24
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Transaction {
}
