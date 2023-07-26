package com.zz.leetcode.easy100;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description TwoSum
 * @Author 张卫刚
 * @Date Created on 2023/7/26
 */
public class TwoSum {


    public static int[] getTwoSum(int[] nums, int target) {
        int len = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }


    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 13;
        System.out.println(Arrays.toString(getTwoSum(nums, target)));
    }
}
