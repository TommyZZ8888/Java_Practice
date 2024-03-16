package com.zz.leetcode.easy100.leetcode.editor.cn;

import java.util.Arrays;

/**
 * @Describtion: TwoToOne
 * @Author: 张卫刚
 * @Date: 2024/3/14 20:15
 */
public class TwoToOne {
    public static void main(String[] args) {
        String str  = "[[1,2],[3,4,5]]";
        String replaceAll = str.replaceAll("\\[", "").replaceAll("]", "");
        String[] split = replaceAll.split(",");
        System.out.println(Arrays.toString(Arrays.stream(split).toArray()));

        String abc = "abc";
        char[] charArray = abc.toCharArray();

        for (char c : charArray) {
            System.out.println(c);
            System.out.println((int) c);
           int j = ((int) c) + 1;
           System.out.println((char) j);
        }
            }
}
