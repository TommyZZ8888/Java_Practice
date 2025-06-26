package com.zzz.niceutil;

import com.zzz.niceutil.service.methodHandle.MethodHandleTest;
import com.zzz.niceutil.utils.MethodHandler;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class NiceutilApplicationTests {

    @Test
    void contextLoads() throws Throwable {
        // 定义参数类型数组
        Class<?>[] paramTypes = {String.class};
        // 实际参数值
        Object[] args = {"World"};
        MethodHandleTest methodHandleTest = new MethodHandleTest();
        // 调用目标对象的 greet 方法
        Object result = MethodHandler.invokeInstanceMethod(
                methodHandleTest,           // 目标对象
                "sayHello",            // 方法名
                String.class,       // 返回类型
                paramTypes,         // 参数类型数组
                args                // 方法参数
        );

        System.out.println(result);
}


}
