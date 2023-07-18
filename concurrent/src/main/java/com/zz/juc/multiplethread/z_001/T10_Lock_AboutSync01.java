package com.zz.juc.multiplethread.z_001;

/**
 * @Description 不要以字符串常量作为锁定对象
 * 下面 m1 m2其实锁的是同一个对象
 * 问题：比如你使用了一个类库，类库中使用代码锁定了字符串“hello”
 * 但是你不知道，你在自己的代码中也锁定了“hello”，就会造成死锁
 * @Author 张卫刚
 * @Date Created on 2023/7/5
 */
public class T10_Lock_AboutSync01 {
   String s1 = "hello";
   String s2 = "hello";

   void m(){
       synchronized (s1){}
   }

   void m2(){
       synchronized (s2){}
   }
}
