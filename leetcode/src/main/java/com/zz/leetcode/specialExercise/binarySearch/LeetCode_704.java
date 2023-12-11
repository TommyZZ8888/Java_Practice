package com.zz.leetcode.specialExercise.binarySearch;

/**
 * @Description LeetCode_704
 * @Author 张卫刚
 * @Date Created on 2023/4/27
 */

/**
 * 给定一个n个元素有序的（升序）整型数组nums 和一个目标值target，写一个函数搜索nums中的 target，如果目标值存在返回下标，否则返回 -1。
 * 示例 1:
 * 输入: nums = [-1,0,3,5,9,12], target = 9
 * 输出: 4
 * 解释: 9 出现在 nums 中并且下标为 4
 * 输入: nums = [-1,0,3,5,9,12], target = 2
 * 输出: -1
 * 解释: 2 不存在 nums 中因此返回 -1
 */
public class LeetCode_704 {
    public static void main(String[] args) {
        int[] nums = new int[]{-1, 0, 3, 5, 9, 12};
        LeetCode_704 leetCode_704 = new LeetCode_704();
        System.out.println(leetCode_704.niceSolution2(nums, 9));

    }

//
//    public int search(int[] nums, int target) {
//        int left = 0;
//        int right = nums.length - 1;
//
//        while (left < right) {
//            int mid = (left + right) / 2;
//            if (nums[mid] == target) {
//                return mid;
//            } else if (nums[mid] < target) {
//                left = mid + 1;
//            } else {
//                right = mid - 1;
//            }
//        }
//        return -1;
//    }
//
//
//    public int search2(int[] nums, int target) {
//        int left = 0;
//        int right = nums.length;
//
//        while (left < right) {
//            int mid = (left + right) / 2;
//            if (nums[mid] == target) {
//                return mid;
//            } else if (nums[mid] < target) {
//                left = mid + 1;
//            } else {
//                right = mid;
//            }
//        }
//        return -1;
//    }

    /**
     * 想清楚二分的是那个端点/边界
     * 而不是大于小于 模板
     * @param nums
     * @param target
     * @return
     */
    public int niceSolution1(int[] nums, int target) {
        int n = nums.length;
        int l = 0, r = n - 1;

        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (nums[mid] <= target) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return nums[r] == target ? r : -1;
    }


    public int niceSolution2(int[] nums, int target) {
        int n = nums.length;
        int l = 0, r = n - 1;
        while (l < r) {
            int mid = l + r >> 1;
            if (nums[mid] >= target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return nums[r] == target ? r : -1;
    }
}
