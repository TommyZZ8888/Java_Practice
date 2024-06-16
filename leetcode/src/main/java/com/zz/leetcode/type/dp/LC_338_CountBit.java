package com.zz.leetcode.type.dp;

import java.util.Arrays;

/**
 * @Describtion: LC_338_CountBit
 * @Author: 张卫刚
 * @Date: 2024/6/16 20:53
 */
public class LC_338_CountBit {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(5)));
        System.out.println((3 & 1) == 0);
    }

    /**
     * 所有数字分为两种
     * 奇数：二进制表示中，奇数一定比前面那个偶数多一个 1，因为多的就是最低位的 1。
     *偶数：二进制表示中，偶数中 1 的个数一定和除以 2 之后的那个数一样多。因为最低位是 0，除以 2 就是右移一位，也就是把那个 0 抹掉而已，所以 1 的个数是不变的。
     *
     * @param n
     * @return
     */
    private static int[] solution(int n) {
        int[] res = new int[n + 1];
        res[0] = 0;
        for (int i = 1; i <= n; i++) {
            //位与运算 替代 i % 2 效率更好
            if ((i & 1)==0) {
                res[i] = res[i >> 1];
            } else {
                res[i] = res[i - 1] + 1;
            }
        }
        return res;
    }
}
