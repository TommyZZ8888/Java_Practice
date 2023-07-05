package com.zz.juc.multiplethread.z_002;


import java.util.ArrayList;
import java.util.List;

/**
 * @Description Synchronized可以保证原子性和可见性，volatile只能保证可见性
 * @Author 张卫刚
 * @Date Created on 2023/7/4
 */
public class T05_Volatile_improve02 {

     int count = 0;

   synchronized void m(){
        for (int i = 0; i < 1000; i++) {
            count++;
        }
    }

    public static void main(String[] args) {
        T05_Volatile_improve02 t = new T05_Volatile_improve02();
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(t::m,"thread-"+i));
        }

        threads.forEach(Thread::start);

        threads.forEach(o->{
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(t.count);
    }
}