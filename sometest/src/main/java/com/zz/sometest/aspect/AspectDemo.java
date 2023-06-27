package com.zz.sometest.aspect;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description AspectDemo
 * @Author 张卫刚
 * @Date Created on 2023/6/27
 */
@OperationLog
@RestController
public class AspectDemo {

    /**
     * doAround执行前
     * doBefore
     * start
     * doAfterReturning
     * doAfter
     * doAround执行中
     * doAround执行后
     */
    @PostMapping("/test")
    public void aspectTest() {
        System.out.println("start");
    }

    /**
     * doAround执行前
     * doBefore
     * doAfterThrowing
     * doAfter
     * doAround 异常后
     * doAround 执行后
     */
    @PostMapping("/testErr")
    public void aspectErrTest() {
        int i = 5 / 0;
        System.out.println(i);
    }
}
