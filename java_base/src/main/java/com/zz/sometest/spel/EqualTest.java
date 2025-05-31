package com.zz.sometest.spel;

/**
 * @Describtion: EqualTest
 * @Author: 张卫刚
 * @Date: 2023/7/22 11:56
 */
public class EqualTest {
    public static void main(String[] args) {
        User zz = new User("zz", 111);
        User zzz = new User("zz", 111);

        System.out.println(zz.equals(zzz));
        System.out.println(zz.hashCode());
        System.out.println(zzz.hashCode());
    }
}
