package com.zz.juc.multiplethread.z_001;


/**
 * @Description Volatile引用类型（包含数组）只能保证引用本身的可见性，不能保证内部字段的可见性
 * @Author 张卫刚
 * @Date Created on 2023/7/4
 */
public class T11_Volatile_extend02 {

    private static class Data {
        int x;
        int y;

        public Data(int a, int b) {
            this.x = a;
            this.y = b;
        }
    }


    volatile static Data data;

    public static void main(String[] args) {
        Thread write = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                data = new Data(i, i);
            }
        });

        Thread read = new Thread(() -> {
            while (data == null) {
            }
            int a = data.x;
            int b = data.y;
            if (a != b) {
                System.out.printf("a = %d, b=%d%n", a, b);
            }
        });

        write.start();
        read.start();

        try {
            write.join();
            read.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("end");
    }
}