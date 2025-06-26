package com.zzz.niceutil.service.methodHandle;

import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Describtion: MethodHandleTest
 * @Author: 张卫刚
 * @Date: 2025/6/26 16:45
 */
@Service
public class MethodHandleTest {


	public String sayHello(String name) {
		return "Hello, " + name;
	}
}

