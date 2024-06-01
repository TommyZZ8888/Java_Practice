package com.zz.leetcode.type.array;

/**
 * @Describtion: 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串的第一个匹配项的下标（下标从 0 开始）。
 * 如果 needle 不是 haystack 的一部分，则返回  -1
 * @Author: 张卫刚
 * @Date: 2024/3/18 21:06
 */
public class LC_28_StrStr {
    public static void main(String[] args) {

        System.out.println(solution2("sadbutsad", "sad"));
    }

    public static int solution1(String haystack, String needle) {
        int hLen = haystack.length();
        int nLen = needle.length();
        for (int i = 0; i + nLen < hLen; i++) {
            boolean isFlag = true;
            for (int j = 0; j < nLen; j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    isFlag = false;
                    break;
                }
            }
            if (isFlag) {
                return i;
            }
        }
        return -1;
    }

    public static int solution2(String haystack, String needle) {
        int m = haystack.length(), n = needle.length();
        char[] ss = haystack.toCharArray(), mm = needle.toCharArray();

        for (int i = 0; i < m - n; i++) {
            int a = i, b = 0;
            while (b < n && ss[a] == mm[b]) {
                a++;
                b++;
            }
            if (b == n) return i;
        }
        return -1;
    }

    /**
     * kmp 解法
     * @param haystack
     * @param needle
     * @return
     */
    public static int solution3(String haystack, String needle) {
        int m = haystack.length(), n = needle.length();
        char[] ss = haystack.toCharArray(), mm = needle.toCharArray();

        for (int i = 0; i < m - n; i++) {
            int a = i, b = 0;
            while (b < n && ss[a] == mm[b]) {
                a++;
                b++;
            }
            if (b == n) return i;
        }
        return -1;
    }

}
