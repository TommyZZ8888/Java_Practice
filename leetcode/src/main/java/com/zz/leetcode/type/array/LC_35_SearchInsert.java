package com.zz.leetcode.type.array;

import java.util.List;

/**
 * @Describtion: LC_35_searchInsert 二分算法
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * @Author: 张卫刚
 * @Date: 2024/4/27 13:50
 */
public class LC_35_SearchInsert {
    public static void main(String[] args) {
        System.out.println(solution3(new int[]{1, 3, 5, 6}, 5));
    }

    public static int solution1(int[] nums, int target) {
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            if (nums[i] == target) {
                return i;
            } else if (nums[i] > target) {
                return i;
            }
        }
        return length;
    }

    public static int solution2(int[] nums, int target) {
        int length = nums.length;
        if (nums[length - 1] < target) {
            return length;
        }
        int left = 0, right = length - 1;
        while (left < right) {
            int mid = left + right >> 1;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static int solution3(int[] nums, int target) {
        int len = nums.length;
        int left = 0, right = len - 1;

        while (left < right) {
            int mid = left + right + 1 >> 1;
            if (nums[mid] <= target) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return nums[left] == target ? left : left + 1;
    }

}
