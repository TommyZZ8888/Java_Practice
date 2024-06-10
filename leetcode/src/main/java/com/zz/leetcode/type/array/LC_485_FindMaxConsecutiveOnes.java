package com.zz.leetcode.type.array;

/**
 * @Describtion: LC_485_FindMaxConsecutiveOnes
 * @Author: 张卫刚
 * @Date: 2024/6/10 13:52
 */
public class LC_485_FindMaxConsecutiveOnes {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 0, 1, 1, 1};
        System.out.println(solution1(nums));
    }


    public static int solution1(int[] nums) {
        int max = 0;
        int ans = 0;
        for (int num : nums) {
            if (num == 1) {
                ans++;
            } else {
                max = Math.max(max, ans);
                ans = 0;
            }
        }
        return Math.max(max, ans);
    }
}
