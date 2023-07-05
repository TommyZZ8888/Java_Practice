package com.zz.juc.multiplethread.z_002;


import java.util.ArrayList;
import java.util.List;

/**
 * @Description Volatile不能保证多个线程共同修改同一变量时所带来的不一致的问题
 * 也就是讲volatile不能替代synchronized
 * @Author 张卫刚
 * @Date Created on 2023/7/4
 */
public class T04_Volatile_improve01 {

    volatile int count = 0;

    void m(){
        for (int i = 0; i < 1000; i++) {
            count++;
        }
    }

    public static void main(String[] args) {
        T04_Volatile_improve01 t = new T04_Volatile_improve01();
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