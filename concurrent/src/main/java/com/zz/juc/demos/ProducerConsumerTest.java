package com.zz.juc.demos;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description ProducerConsumerTest
 * @Author 张卫刚
 * @Date Created on 2023/7/26
 */
public class ProducerConsumerTest {

    private int count = 0;

    private int maxCapacity = 10;

   static LinkedBlockingQueue queue = new LinkedBlockingQueue();


   //method 3: blockingQueue
    public static void main(String[] args) {

        new Thread(()->{
            for (int i = 0; i < 20; i++) {
                try {
                    queue.put(i);
                    System.out.println("producer: "+i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        new Thread(()->{
            for (int i = 0; i < 20; i++) {
                try {
                    System.out.println("conusmer: "+i);
                    queue.take();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();


    }

    /** method 2: lock condition await signal
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void producer() {
        lock.lock();
        try {
            while (count >= maxCapacity) {
                condition.await();
            }
            count++;
            System.out.println("producer: " + count);
            condition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void consumer() {
        lock.lock();
        try {
            while (count == 0) {
                condition.await();
            }
            System.out.println(Thread.currentThread().getName() + ": " + count);
            count--;
            condition.signal();
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ProducerConsumerTest pc = new ProducerConsumerTest();

        new Thread(()->{
            for (int i = 0; i < 20; i++) {
                pc.producer();
            }
        },"producer").start();

        new Thread(()->{
            for (int i = 0; i < 20; i++) {
                pc.consumer();
            }
        },"consumer").start();
    }
*/

/** method 1 :wait notifyALl  synchronized
 public synchronized void producer() {
 while (count >= maxCapacity) {
 try {
 wait();
 } catch (InterruptedException e) {
 throw new RuntimeException(e);
 }
 }

 count++;
 System.out.println("producer : " + count);
 notifyAll();
 }


 public synchronized void consumer() {
 while (count == 0) {
 try {
 wait();
 } catch (InterruptedException e) {
 throw new RuntimeException(e);
 }
 }
 count--;
 System.out.println(Thread.currentThread().getName() + "================" + count);
 notifyAll();
 }

 public static void main(String[] args) {
 ProducerConsumerTest pc = new ProducerConsumerTest();
 new Thread(() -> {
 for (int i = 0; i < 20; i++) {
 pc.producer();
 }
 }).start();

 new Thread(() -> {
 for (int i = 0; i < 20; i++) {
 pc.consumer();
 }
 }, "consumer1").start();


 new Thread(() -> {
 for (int i = 0; i < 20; i++) {
 pc.consumer();
 }
 }, "consumer2").start();
 }
 */
}
