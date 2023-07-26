package com.zz.leetcode.specialExercise;

import java.util.Arrays;

/**
 * @Description BubbleSort
 * @Author 张卫刚
 * @Date Created on 2023/7/26
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = new int[]{2, 4, 3, 1};
        solution(arr);

        Arrays.stream(arr).forEach(System.out::println);
    }


    public static void solution(int[] nums) {
        int len = nums.length;

        for (int i = 0; i < len - 1; i++) {



            for (int j = 0; j < len - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }
    }
}
