package com.zz.leetcode.type.dp;

/**
 * @Describtion: LC_1137_Tribonacci
 * 泰波那契序列 Tn 定义如下：
 * T0 = 0, T1 = 1, T2 = 1, 且在 n >= 0 的条件下 Tn+3 = Tn + Tn+1 + Tn+2
 * 给你整数 n，请返回第 n 个泰波那契数 Tn 的值。
 * <p>
 * 示例 1：
 * 输入：n = 4
 * 输出：4
 * 解释：
 * T_3 = 0 + 1 + 1 = 2
 * T_4 = 1 + 1 + 2 = 4
 * <p>
 * 示例 2：
 * 输入：n = 25
 * 输出：1389537
 * @Author: 张卫刚
 * @Date: 2024/11/2 10:50
 */
public class LC_1137_Tribonacci {
	public static void main(String[] args) {
		System.out.println(solution1(3));
		System.out.println(solution2(25));
		System.out.println(solution3(25));
	}

	public static int solution1(int n) {
		if (n == 0) return 0;
		if (n == 1 || n == 2) return 1;
		int[] dp = new int[n + 1];
		dp[0] = 0;
		dp[1] = 1;
		dp[2] = 1;
		for (int i = 3; i <= n; i++) {
			dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
		}
		return dp[n];
	}

	public static int solution2(int n) {
		if (n == 0) return 0;
		if (n == 1 || n == 2) return 1;
		int a = 0, b = 1, c = 1;
		for (int i = 3; i <= n; i++) {
			int temp = c;
			c = a + b + c;
			a = b;
			b = temp;
		}
		return c;
	}

	// 优化空间复杂度
	public static int solution3(int n) {
		if (n == 0) return 0;
		if (n == 1 || n == 2) return 1;
		int ans = 0, a = 0, b = 1, c = 1;
		for (int i = 3; i <= n; i++) {
			ans = a + b + c;
			a = b;
			b = c;
			c = ans;
		}
		return ans;
	}
}
