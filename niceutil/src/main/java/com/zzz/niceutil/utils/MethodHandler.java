package com.zzz.niceutil.utils;

/**
 * @Describtion: MethodInvoke
 * @Author: 张卫刚
 * @Date: 2025/4/1 18:39
 */

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 高性能方法调用工具类（基于MethodHandle实现）
 * 线程安全 | 缓存优化 | 异常处理增强
 */
public class MethodHandler {

	private static final ConcurrentHashMap<String, MethodHandle> METHOD_HANDLE_CACHE = new ConcurrentHashMap<>();
	/**
	 * 调用实例方法（支持缓存）
	 *
	 * @param target      目标对象
	 * @param methodName  方法名
	 * @param returnType  返回值类型
	 * @param paramTypes  参数类型数组
	 * @param args        方法参数
	 * @return 方法调用结果
	 * @throws Throwable 如果调用失败
	 */
	public static Object invokeInstanceMethod(Object target, String methodName, Class<?> returnType, Class<?>[] paramTypes, Object... args) throws Throwable {
		// 构建缓存键
		String cacheKey = buildCacheKey(target.getClass(), methodName, paramTypes);

		// 查找或创建方法句柄
		MethodHandle methodHandle = METHOD_HANDLE_CACHE.computeIfAbsent(cacheKey, key -> {
			try {
				// 定义方法签名
				MethodType methodType = MethodType.methodType(returnType, paramTypes);

				// 查找实例方法句柄
				return MethodHandles.lookup().findVirtual(target.getClass(), methodName, methodType);
			} catch (NoSuchMethodException | IllegalAccessException e) {
				throw new RuntimeException("Failed to find method: " + methodName, e);
			}
		});

		// 调用方法
		return methodHandle.bindTo(target).invokeWithArguments(args);
	}

	/**
	 * 调用静态方法（支持缓存）
	 *
	 * @param clazz       目标类
	 * @param methodName  方法名
	 * @param returnType  返回值类型
	 * @param paramTypes  参数类型数组
	 * @param args        方法参数
	 * @return 方法调用结果
	 * @throws Throwable 如果调用失败
	 */
	public static Object invokeStaticMethod(Class<?> clazz, String methodName, Class<?> returnType, Class<?>[] paramTypes, Object... args) throws Throwable {
		// 构建缓存键
		String cacheKey = buildCacheKey(clazz, methodName, paramTypes);

		// 查找或创建方法句柄
		MethodHandle methodHandle = METHOD_HANDLE_CACHE.computeIfAbsent(cacheKey, key -> {
			try {
				// 定义方法签名
				MethodType methodType = MethodType.methodType(returnType, paramTypes);

				// 查找静态方法句柄
				return MethodHandles.lookup().findStatic(clazz, methodName, methodType);
			} catch (NoSuchMethodException | IllegalAccessException e) {
				throw new RuntimeException("Failed to find static method: " + methodName, e);
			}
		});

		// 调用方法
		return methodHandle.invokeWithArguments(args);
	}

	/**
	 * 构建缓存键
	 *
	 * @param clazz      类对象
	 * @param methodName 方法名
	 * @param paramTypes 参数类型数组
	 * @return 缓存键字符串
	 */
	private static String buildCacheKey(Class<?> clazz, String methodName, Class<?>[] paramTypes) {
		StringBuilder keyBuilder = new StringBuilder();
		keyBuilder.append(clazz.getName()).append("#").append(methodName).append("(");
		for (Class<?> paramType : paramTypes) {
			keyBuilder.append(paramType.getName()).append(",");
		}
		if (paramTypes.length > 0) {
			keyBuilder.setLength(keyBuilder.length() - 1); // 移除最后一个逗号
		}
		keyBuilder.append(")");
		return keyBuilder.toString();
	}
}