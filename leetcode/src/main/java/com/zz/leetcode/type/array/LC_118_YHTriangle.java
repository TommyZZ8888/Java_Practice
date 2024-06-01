package com.zz.leetcode.type.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @Describtion: 杨辉三角
 * @Author: 张卫刚
 * @Date: 2024/6/1 10:49
 */
public class LC_118_YHTriangle {
    public static void main(String[] args) {
        System.out.println(solution1(5));
    }

    public static List<List<Integer>> solution1(int numRows){
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> list  = new ArrayList<>();
            for (int j = 0; j <= i ; j++) {
                if (j == 0 || j == i){
                    list.add(1);
                }else {
                    list.add(res.get(i - 1).get(j - 1) + res.get(i - 1).get(j));
                }
            }
            res.add(list);
        }
        return res;
    }
}
