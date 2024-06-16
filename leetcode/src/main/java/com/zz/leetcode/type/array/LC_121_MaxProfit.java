package com.zz.leetcode.type.array;

/**
 * @Describtion: LC_121_MaxProfit
 * @Author: 张卫刚
 * @Date: 2024/6/12 20:30
 */
public class LC_121_MaxProfit {
    public static void main(String[] args) {
        int[] nums = {7, 2, 5, 3, 6, 1};
        System.out.println(solution2(nums));
    }

    /**
     * 暴力解法  ..超时
     *
     * @param nums
     * @return
     */
    public static int solution(int[] nums) {
        int max = 0;
        int maxProfit = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] > nums[i]) {
                    max = Math.max(max, nums[j] - nums[i]);
                }
            }
            maxProfit = Math.max(maxProfit, max);
        }
        return maxProfit;
    }

    public static int solution2(int[] nums) {
        int min = nums[0];
        int max = 0;
        int temp;
        for (int i = 1; i < nums.length; i++) {
            temp = nums[i] - min;
            max = Math.max(max, temp);
            min = Math.min(min, nums[i]);
        }
        return max;
    }
}
