package com.zz.leetcode.type.array;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Describtion: LC_26_RemoveDuplicates
 * 删除有序数组中的重复数:  (所给数组是非严格递增)
 * @Author: 张卫刚
 * @Date: 2024/3/17 19:22
 */
public class LC_26_RemoveDuplicates {
    public static void main(String[] args) {
        int[] ints = {1, 1, 2};
        System.out.println(solution4(ints, 2, ints[ints.length - 1]));
    }


    public static int solution1(int[] nums) {
        Set<Integer> collect = Arrays.stream(nums).boxed().collect(Collectors.toSet());
        System.out.println(collect);
        return collect.size();
    }

    public static int solution2(int[] nums) {
        List<Integer> list = new LinkedList<>();

        for (int num : nums) {
            if (!list.contains(num)) {
                list.add(num);
            }
        }
        System.out.println(list);
        return list.size();
    }

    /**
     * 双指针
     *
     * @param nums
     * @return
     */
    public static int solution3(int[] nums) {
        int length = nums.length;
        int j = 0;

        for (int i = 0; i < length; i++) {
            if (nums[i] != nums[j]) {
                nums[++j] = nums[i];
            }
        }
        return j + 1;
    }

    /**
     * 通用解法
     *
     * @param nums 数组
     * @param k 要保留的相同数字的个数
     * @param max 数组中最大值
     * @return
     */
    public static int solution4(int[] nums, int k, int max) {
        int idx = 0;
        for (int num : nums) {
            if (idx < k || nums[idx - k] != num) nums[idx++] = num;
            if (idx >= k && nums[idx - k] == max) break;
        }
        return idx;
    }

}
