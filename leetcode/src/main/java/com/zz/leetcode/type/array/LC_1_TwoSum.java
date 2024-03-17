package com.zz.leetcode.type.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description LC_1_TwoSum
 * @Author 张卫刚
 * @Date Created on 2023/7/26
 */
public class LC_1_TwoSum {


    /**
     * hash表
     * @param nums
     * @param target
     * @return
     */
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

    /**
     * 暴力枚举
     * @param nums
     * @param target
     * @return
     */
    public static int[] solution2(int[] nums, int target){
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            for (int j = i+1; j < length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }


    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 13;
        System.out.println(Arrays.toString(solution2(nums, target)));
    }
}
