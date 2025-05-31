package com.zz.sometest.functionInterface;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @Describtion: 四大函数式接口
 * @Author: 张卫刚
 * @Date: 2025/5/31 9:15
 */
public class Main {
	public static void main(String[] args) {
		printTest();
	}


	/**
	 * function
	 */
	public static void actionFunction() {
		Function<Integer, String> function = new Function<>() {
			@Override
			public String apply(Integer integer) {
				return integer + " hello function";
			}
		};
		System.out.println(function.apply(10));
	}

	public static int testFunction(int a, Function<Integer, Integer> function) {
		return function.apply(a);
	}

	public static void printTestFunction() {
		System.out.println(testFunction(10, x -> x + 10));
		actionFunction();
	}

	/**
	 * consumer
	 */
	public static void actionConsumer() {
		Consumer<Object> consumer = new Consumer<>() {
			@Override
			public void accept(Object o) {
				System.out.println(o + " hello consumer");
			}
		};
		consumer.accept("hello");
	}

	public static void testConsumer(Object o, Consumer<Object> consumer) {
		consumer.accept(o);
	}

	public static void printTestConsume() {
		testConsumer("hello", s -> System.out.println(s + " world"));
		actionConsumer();
	}

	/**
	 * predicate
	 */
	public static void actionPredicate() {
		Predicate<Integer> predicate = new Predicate<>() {
			@Override
			public boolean test(Integer integer) {
				return false;
			}
		};
		System.out.println(predicate.test(10));
	}

	public static boolean testPredicate(int a, Predicate<Integer> predicate) {
		return predicate.test(a);
	}

	public static void printTestPredicate() {
		System.out.println(testPredicate(10, x -> x % 2 == 0));
		actionPredicate();
	}

	/**
	 * supplier
	 */
	public static void actionSupplier() {
		Supplier<Object> supplier = new Supplier<>() {
			@Override
			public Object get() {
				return "hello supplier";
			}
		};
		System.out.println(supplier.get());
	}

	public static boolean testSupplier(Supplier<String> supplier) {
		return Objects.equals(supplier.get(), "hello supplier");
	}

	public static void printTestSupplier() {
		System.out.println(testSupplier(() -> "hello supplier"));
		actionSupplier();
	}


	/**
	 * 自定义
	 */
	public static void action(boolean flag, String str, Consumer<Object> consumer) {
		if (flag) {
			consumer.accept(str);
		}
	}

	public static void printTest() {
		action(true, "hello", s -> System.out.println(s + " world"));
	}

}
