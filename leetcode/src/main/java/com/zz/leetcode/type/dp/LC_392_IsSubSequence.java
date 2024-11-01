package com.zz.leetcode.type.dp;

/**
 * @Describtion: LC_392_IsSubSequence
 * @Author: 张卫刚
 * @Date: 2024/6/29 20:54
 */
public class LC_392_IsSubSequence {
    public static void main(String[] args) {
        System.out.println(solution("b", "abc"));
    }

    public static boolean solution(String s, String t) {
        char[] tArray = t.toCharArray();
        char[] sArray = s.toCharArray();
        int len = sArray.length;
        if (len == 0) return false;
        int j = 0;
        for (char c : tArray) {
            if (j == len) return true;
            if (c == sArray[j]) {
                j++;
            }
        }
        return j == len;
    }
}
