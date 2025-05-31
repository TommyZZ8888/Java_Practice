package com.zz.leetcode.type.array;

/**
 * @Describtion: 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度
 * @Author: 张卫刚
 * @Date: 2024/3/18 21:06
 */
public class LC_27_RemoveElement {
    public static void main(String[] args) {
        int[] ints = {3, 2, 3, 2};
        System.out.println(solution2(ints, 3));

//        solution3(ints,3);
    }

    public static int solution1(int[] nums, int val) {
        int len = nums.length;
        for (int num : nums) {
            if (num == val) {
                len--;
            }
        }
        return len;
    }

    public static int solution2(int[] nums, int val){
        int ans = 0;

        for(int num: nums){
            if(num != val){
                nums[ans++] = num;

            }
        }
        return ans;
    }

    /**
     * 双指针
     * @param nums
     * @param val
     * @return
     */
    public static int solution3(int[] nums, int val){
        int j = nums.length - 1;

        for (int i = 0; i < j; i++) {
            if(nums[i] == val){
                swap(nums,i--,j--);
            }
        }
        return j+1;
    }

    public static void swap(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
