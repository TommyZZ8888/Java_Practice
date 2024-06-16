package com.zz.leetcode.type.array;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Describtion: LC_349_Intersection
 * @Author: 张卫刚
 * @Date: 2024/6/11 21:51
 */
public class LC_349_Intersection {
    public static void main(String[] args) {
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};
        System.out.println(Arrays.toString(solution(nums1, nums2)));
    }

    public static int[] solution(int[] nums1, int[] nums2) {
        nums1= Arrays.stream(nums1).distinct().toArray();
        nums2= Arrays.stream(nums2).distinct().toArray();
        int[] finalNums = nums2;
        return Arrays.stream(nums1).filter(item -> Arrays.stream(finalNums).anyMatch(i -> i == item)).distinct().toArray();
    }

    public static int[] solution2(int[] nums1, int[] nums2) {
        Set<Integer> collect = Arrays.stream(nums1).boxed().collect(Collectors.toSet());
        return Arrays.stream(nums2).filter(collect::contains).distinct().toArray();
    }
}
