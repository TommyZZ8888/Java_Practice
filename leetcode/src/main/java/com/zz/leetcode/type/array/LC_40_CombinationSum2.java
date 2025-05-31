package com.zz.leetcode.type.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Describtion: LC_40_CombinationSum2
 * @Author: 张卫刚
 * @Date: 2024/6/5 20:41
 */
public class LC_40_CombinationSum2 {
    private static List<List<Integer>> ans = new ArrayList<>();

    private static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        int[] nums = new int[]{10, 1, 2, 7, 6, 1, 5};
        //关键点，减少重复/不继续向下进行
        Arrays.sort(nums);
        solution(nums, 8, 0);
        System.out.println(ans);
    }

    /**
     * 超内存限制
     * @param nums
     * @param target
     * @param start
     */
    public static void solution(int[] nums, int target, int start) {
        if (target == 0) {
            ans.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            if (target < nums[i]) {
                break;
            }
            //关键一步
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            list.add(nums[i]);
            solution(nums, target - nums[i], i + 1);
            list.removeLast();
        }
    }

}
