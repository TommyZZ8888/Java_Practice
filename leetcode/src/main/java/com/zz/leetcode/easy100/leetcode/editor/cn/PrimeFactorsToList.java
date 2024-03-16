package com.zz.leetcode.easy100.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

/**
 * @Describtion: PrimeFactorsToList
 * @Author: 张卫刚
 * @Date: 2024/3/14 20:11
 */
public class PrimeFactorsToList {

    public static List<Integer> integerToPrimeFactors(int number) {
        List<Integer> primeFactors = new ArrayList<>();

        // 对于2这个特殊的质数进行处理
        while (number % 2 == 0) {
            primeFactors.add(2);
            number /= 2;
        }

        // 遍历从3开始的所有奇数，直到这个数的平方根
        for (int i = 3; i <= Math.sqrt(number); i += 2) {
            while (number % i == 0) {
                primeFactors.add(i);
                number /= i;
            }
        }

        // 如果此时number大于2，则它本身就是个质数
        if (number > 2) {
            primeFactors.add(number);
        }

        return primeFactors;
    }

    public static void main(String[] args) {
        int inputNumber = 90; // 示例整数
        List<Integer> factors = integerToPrimeFactors(inputNumber);
        System.out.println("The prime factors of " + inputNumber + " are: " + factors);
        /**
         * 字符+1
         * 质数
         * 两个数组--》一个
         *
         * 质数
         */
    }
}
