package com.zz.juc.multiplethread.z_001;

import sun.misc.Unsafe;

/**
 * @Description T01_Unsafe
 * @Author 张卫刚
 * @Date Created on 2023/7/10
 */
public class T07_Unsafe {

    static class M{
        private M(){}
        int i = 0;
    }

    public static void main(String[] args) throws InstantiationException {
        Unsafe unsafe = Unsafe.getUnsafe();
        M m =(M) unsafe.allocateInstance(M.class);
        m.i = 9;
        System.out.println(m.i);
    }
}
