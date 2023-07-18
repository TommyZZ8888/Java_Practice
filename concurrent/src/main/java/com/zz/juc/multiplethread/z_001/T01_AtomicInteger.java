package com.zz.juc.multiplethread.z_001;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Description Atomic类本身都是原子性的，但不能保证多个方法连续调用都是原子性的
 * @Author 张卫刚
 * @Date Created on 2023/7/10
 */
public class T01_AtomicInteger {

  AtomicInteger count = new AtomicInteger(0);


  public void m(){
      for (int i = 0; i < 10000; i++) {
          count.incrementAndGet();
      }
  }

    public static void main(String[] args) {
        T01_AtomicInteger t = new T01_AtomicInteger();
        List<Thread> collect = IntStream.rangeClosed(1, 10).mapToObj(o -> new Thread(t::m, "thread-" + o)).collect(Collectors.toList());
        collect.forEach(Thread::start);
        collect.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println(t.count);
    }
}
