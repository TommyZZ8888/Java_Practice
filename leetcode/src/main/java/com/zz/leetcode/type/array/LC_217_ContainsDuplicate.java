package com.zz.leetcode.type.array;


import java.util.*;

/**
 * @Describtion: LC_217_ContainsDuplicate
 * @Author: 张卫刚
 * @Date: 2024/6/9 15:43
 */
public class LC_217_ContainsDuplicate {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 3};
        System.out.println(solution2(nums));
    }

    /**
     * 最快
     *
     * @param nums
     * @return
     */
    public static boolean solution(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!set.add(num)) {
                return true;
            }
        }
        return false;
    }

    public static boolean solution1(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                return true;
            }
            map.put(nums[i], i);
        }
        return false;
    }

    public static boolean solution2(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }
        return false;
    }

    public static boolean solution3(int[] nums) {
        return Arrays.stream(nums).distinct().count() != nums.length;
    }
}

