package com.zz.leetcode.type.dp;

/**
 * @Describtion: LC_509_Fib
 * @Author: 张卫刚
 * @Date: 2024/6/16 20:42
 */
public class LC_509_Fib {
    public static void main(String[] args) {
        System.out.println(solution(4));
    }

    private static int solution(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        return solution(n - 1) + solution(n - 2);
    }

    private static int solution2(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int a = 1, b = 1, c = 0;
        for (int i = 3; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }
}
