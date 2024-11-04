package com.zz.leetcode.type.dp;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @Describtion: LC_1668_MaxRepeat
 * 给你一个字符串 sequence ，如果字符串 word 连续重复 k 次形成的字符串是 sequence 的一个子字符串，那么单词 word 的 重复值为 k 。单词 word 的 最大重复值 是单词 word 在 sequence 中最大的重复值。如果 word 不是 sequence 的子串，那么重复值 k 为 0 。
 * 给你一个字符串 sequence 和 word ，请你返回 最大重复值 k 。
 * <p>
 * 示例 1：
 * 输入：sequence = "ababc", word = "ab"
 * 输出：2
 * 解释："abab" 是 "ababc" 的子字符串。
 * 示例 2：
 * 输入：sequence = "ababc", word = "ba"
 * 输出：1
 * 解释："ba" 是 "ababc" 的子字符串，但 "baba" 不是 "ababc" 的子字符串。
 * 示例 3：
 * 输入：sequence = "ababc", word = "ac"
 * 输出：0
 * 解释："ac" 不是 "ababc" 的子字符串
 * @Author: 张卫刚
 * @Date: 2024/11/4 15:20
 */
public class LC_1668_MaxRepeat {
	public static void main(String[] args) {
//		System.out.println(solution1("ababc", "ab"));
//		System.out.println(solution1("ababc", "ba"));
		System.out.println(solution1("aaabaaaabaaabaaaabaaaabaaaabaaaaba", "aaaba"));
	}

	public static int solution1(String sequence, String word) {
		if (!sequence.contains(word)) return 0;
		int length = sequence.length();
		int wordLength = word.length();
		int ans = 0;
		int[] dp = new int[length + 10];
		for (int i = 1; i <= length; i++) {
			if (i - wordLength < 0) continue;
			if (sequence.substring(i - wordLength, i).equals(word)) dp[i] = dp[i - wordLength] + 1;
			ans = Math.max(ans, dp[i]);
		}

		return ans;
	}
}
