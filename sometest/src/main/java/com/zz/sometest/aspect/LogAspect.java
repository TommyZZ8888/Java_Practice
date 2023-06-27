package com.zz.sometest.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @Description LogAspect
 * @Author 张卫刚
 * @Date Created on 2023/6/27
 */

@Aspect
@Component
@Slf4j
public class LogAspect {

    @Pointcut("execution(* com.zz.sometest.aspect..*.*(..))")
    public void logAspectTest() {
    }


    @Before("logAspectTest()")
    public void doBefore(JoinPoint joinPoint) {
        System.out.println("doBefore");
    }

    @After("logAspectTest()")
    public void doAfter() {
        System.out.println("doAfter");
    }

    @Around("logAspectTest()")
    public void doAround(ProceedingJoinPoint joinPoint) {
        System.out.println("doAround执行前");
        try {
            Object proceed = joinPoint.proceed();
            System.out.println("doAround执行中");
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println("doAround 异常后");
        }
        System.out.println("doAround执行后");
    }

    @AfterReturning("logAspectTest()")
    public void doAfterReturning() {
        System.out.println("doAfterReturning");
    }

    @AfterThrowing("logAspectTest()")
    public void doAfterThrowing() {
        System.out.println("doAfterThrowing");
    }
}
