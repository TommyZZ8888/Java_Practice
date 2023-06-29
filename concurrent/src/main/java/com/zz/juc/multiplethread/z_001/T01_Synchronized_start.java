package com.zz.juc.multiplethread.z_001;

/**
 * @Description Synchronized_demo01
 * @Author 张卫刚
 * @Date Created on 2023/6/29
 */
public class T01_Synchronized_start {
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
       synchronized (T01_Synchronized_start.class){
           count--;
           System.out.println(Thread.currentThread().getName()+" count "+ count);
       }
   }

    public static void main(String[] args) {
        T01_Synchronized_start demo01 = new T01_Synchronized_start();
        demo01.test03();
    }
}
