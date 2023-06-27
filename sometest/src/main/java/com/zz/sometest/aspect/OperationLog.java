package com.zz.sometest.aspect;

import java.lang.annotation.*;

/**
 * @Description OperationLog
 * @Author 张卫刚
 * @Date Created on 2023/6/27
 */

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperationLog {
}
