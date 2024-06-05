package com.zz.leetcode.type.array;

import java.util.*;

/**
 * @Describtion: LC_39_CombinationSum
 * @Author: 张卫刚
 * @Date: 2024/6/2 13:43
 */
public class LC_39_CombinationSum {
    public static List<List<Integer>> ans = new ArrayList<>();
    public static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println(solution1(new int[]{2, 3, 5}, 8));
    }

    public static List<List<Integer>> solution1(int[] candidates, int target) {
        Arrays.sort(candidates);
        helper(candidates, target, 0);
        return ans;
    }

    public static void helper(int[] candidates, int target, int start) {
        if (target == 0) {
            ans.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (target<candidates[i]) break;
            list.add(candidates[i]);
            helper(candidates, target - candidates[i], i);
            list.removeLast();
        }
    }

    public static void test(int[] nums,int target,int start){
        if (target==0){
            ans.add(new ArrayList<>(list));
            return;
        }
        for (int i = start;i<nums.length;i++){
            if (target<nums[i])break;
            list.add(nums[i]);
            test(nums,target-nums[i],i);
            list.removeLast();
        }
    }
}
