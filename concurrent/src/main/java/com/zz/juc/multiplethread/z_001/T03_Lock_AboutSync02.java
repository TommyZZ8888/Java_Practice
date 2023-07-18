package com.zz.juc.multiplethread.z_001;

/**
 * @Description 锁定对象o，o的属性改变不会影响锁的使用
 * 但如果o变成了另外一个对象，则锁的对象发生改变
 * @Author 张卫刚
 * @Date Created on 2023/7/5
 */
public class T03_Lock_AboutSync02 {
 Object o = new Object();

 void m(){
     synchronized (o){
         while (true){
             try {
                 Thread.sleep(100);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
             System.out.println(Thread.currentThread().getName());
         }
     }
 }

    public static void main(String[] args) {
        T03_Lock_AboutSync02 t = new T03_Lock_AboutSync02();
        new Thread(t::m,"t1").start();

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread thread = new Thread(t::m, "t2");
        //锁的对象发生改变，所以线程2得以执行，否则线程2永远得不到机会执行
        t.o = new Object();
        thread.start();
    }
}
