package com.zz.juc.multiplethread.z_000;

/**
 * @Description
 * synchronized的底层实现
 * JDK早期的 重量级 - OS
 * 后来的改进
 * 锁升级的概念：
 *     我就是厕所所长 （一 二）
 *
 * sync (Object)
 * markword 记录这个线程ID （偏向锁）
 * 如果线程争用：升级为 自旋锁
 * 10次以后，
 * 升级为重量级锁 - OS
 *
 * 执行时间短（加锁代码），线程数少，用自旋
 * 执行时间长，线程数多，用系统锁
 * @Author 张卫刚
 * @Date Created on 2023/6/29
 */
public class T05_Synchronized_start {
   private int count = 10;
//   private Object o  =new Object();

   public void test(){
       synchronized (this){
           count--;
           System.out.println(Thread.currentThread().getName()+" count "+ count);
       }
   }

   public synchronized void test02(){  //等同于上文synchronized (this)
       count--;
       System.out.println(Thread.currentThread().getName()+" count "+ count);
   }


   public void test03(){
       synchronized (T05_Synchronized_start.class){
           count--;
           System.out.println(Thread.currentThread().getName()+" count "+ count);
       }
   }

    public static void main(String[] args) {
        T05_Synchronized_start demo01 = new T05_Synchronized_start();
        demo01.test03();
    }
}
