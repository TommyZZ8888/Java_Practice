package com.zz.leetcode.type.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Describtion: LC_119_YHTriangle2
 * @Author: 张卫刚
 * @Date: 2024/6/6 20:54
 */
public class LC_119_YHTriangle2 {
    public static void main(String[] args) {
        System.out.println(solution3(3));
    }

    /**
     * simple,consumed a lot of memory
     * @param rowIndex
     * @return
     */
    public static List<Integer> solution1(int rowIndex) {
        List<List<Integer>> collect = new ArrayList<>();
        for (int i = 0; i <= rowIndex; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < i + 1; j++) {
                if (j==0 || j==i){
                    list.add(1);
                }else {
                    list.add(collect.get(i-1).get(j-1)+collect.get(i-1).get(j));
                }
            }
            collect.add(list);
        }
        return collect.getLast();
    }

    /**
     * improve, but still need a lot of memory
     * @param rowIndex
     * @return
     */
    public static List<Integer> solution2(int rowIndex){
        List<Integer> preList = new ArrayList<>();
        for (int i = 0; i <= rowIndex; i++) {
            List<Integer> curList = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j==0 || j==i){
                    curList.add(1);
                }else {
                    curList.add(preList.get(j)+preList.get(j-1));
                }
            }
            preList = curList;
        }
        return preList;
    }

    /**
     * nice version
     * 找规律 list 的size = rowIndex+1。。。。需要点数学天赋才能想到吧..
     * @param rowIndex
     * @return
     */
    public static List<Integer> solution3(int rowIndex) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i <= rowIndex; ++i) {
            ans.add(1);
            for (int j = i - 1; j > 0; --j) {
                ans.set(j, ans.get(j) + ans.get(j - 1));
            }
        }
        return ans;
    }
}
