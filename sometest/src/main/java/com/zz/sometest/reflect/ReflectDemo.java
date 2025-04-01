package com.zz.sometest.reflect;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Describtion: ReflectDemo
 * @Author: 张卫刚
 * @Date: 2025/4/1 18:20
 */
public class ReflectDemo {
	public static void main(String[] args) throws Throwable {
		new ReflectDemo().methodHandleTest();
	}

	/**
	 * 正常的反射调用
	 */
	public void normal() throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		Class clazz = ReflectDemo.class;
		Method method = clazz.getMethod("studyHard", String.class);
		ReflectDemo demo = (ReflectDemo) clazz.newInstance();
		method.invoke(demo, "哈哈");
	}

	public void studyHard(String str) {
		System.out.println("study hard " + str);
	}

	public void methodHandleTest() throws Throwable {
		Class<?> clazz = ReflectDemo.class;
		MethodType signature = MethodType.methodType(String.class, int.class, String.class);
		MethodHandle mh = MethodHandles.lookup().findVirtual(clazz, "test1", signature);

		// 绑定实例对象
		Object obj = clazz.getConstructor().newInstance();
		System.out.println(mh.bindTo(obj).invoke(1, "2"));
		System.out.println((String) mh.bindTo(obj).invokeExact(1, "2"));

		// 绑定类对象
		ReflectDemo reflectDemo = new ReflectDemo();
		System.out.println(mh.invoke(reflectDemo,1, "2"));
		System.out.println((String) mh.invokeExact(reflectDemo,1, "2"));

		//--------------静态方法
		MethodHandle mh2 = MethodHandles.lookup().findStatic(clazz, "test2", signature);
		// 静态方法无需bindTo
		System.out.println(mh2.invoke(1, "2"));
		System.out.println(mh2.bindTo(obj).invoke(1, "2"));
	}


	public String test1(int a, String b) {
		System.out.println("test1 -> " + a + b);
		return a + b;
	}

	public static String test2(int a, String b) {
		System.out.println("test2 -> " + a + b);
		return a + b;
	}
}
