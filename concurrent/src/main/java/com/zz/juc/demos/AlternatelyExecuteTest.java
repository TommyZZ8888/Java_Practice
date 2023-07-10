package com.zz.juc.demos;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description 交替输出a和b，各输出50次，先输出a，两个线程
 * @Author 张卫刚
 * @Date Created on 2023/7/10
 */
public class AlternatelyExecuteTest {
    public static void main(String[] args) {
//        ExecutorService executorService = Executors.newFixedThreadPool(2);
//        PrintTest05 t = new PrintTest05(50, true);
//        executorService.execute(() -> t.turnPrint("A", true, false));
//        executorService.execute(() -> t.turnPrint("B", false, true));

        printTest06();
    }

    /**
     * LockSupport
     */
    public static void printTest01() {
        Thread[] threads = new Thread[2];
        threads[0] = new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                System.out.println("A");
                //恢复指定线程
                LockSupport.unpark(threads[1]);
                //暂停当前线程
                LockSupport.park();
            }
        });

        threads[1] = new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                LockSupport.park();
                System.out.println("B");
                LockSupport.unpark(threads[0]);
            }
        });

        threads[0].start();
        threads[1].start();
    }

    /**
     * Semaphore
     */
    public static void printTest02() {
        Semaphore semaphoreA = new Semaphore(1);
        //先阻塞当前线程,a线程先执行
        Semaphore semaphoreB = new Semaphore(0);

        Thread threadA = new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                try {
                    semaphoreA.acquire();
                    System.out.println("A");
                    semaphoreB.release();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread threadB = new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                try {
                    semaphoreB.acquire();
                    System.out.println("B");
                    semaphoreA.release();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        threadA.start();
        threadB.start();
    }

    /**
     * Condition 1
     */
    public static void printTest03() {
        ReentrantLock lock = new ReentrantLock();
        Condition conditionA = lock.newCondition();
        Condition conditionB = lock.newCondition();

        Thread threadA = new Thread(() -> {
            lock.lock();
            for (int i = 0; i < 50; i++) {
                try {
                    System.out.println("A");
                    conditionB.signal();
                    conditionA.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            lock.unlock();

        });

        Thread threadB = new Thread(() -> {

            lock.lock();
            //确保A先执行输出，不然可能B线程先执行，只会输出一个A，然后一直阻塞
            threadA.start();
            for (int i = 0; i < 50; i++) {
                try {
                    conditionB.await();
                    System.out.println("B");
                    conditionA.signal();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            lock.unlock();
        });

        threadB.start();
    }


    /**
     * Condition 2
     */
    static boolean flag = false;

    public static void printTest04() {
        ReentrantLock lock = new ReentrantLock();
        Condition conditionA = lock.newCondition();
        Condition conditionB = lock.newCondition();


        Thread threadA = new Thread(() -> {
            lock.lock();
            for (int i = 0; i < 50; i++) {
                try {
                    while (flag) {
                        conditionA.await();
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("A");
                flag = true;
                conditionB.signal();
            }
            lock.unlock();

        });

        Thread threadB = new Thread(() -> {
            lock.lock();
            for (int i = 0; i < 50; i++) {
                try {
                    while (!flag) {
                        conditionB.await();
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("B");
                flag = false;
                conditionA.signal();

            }
            lock.unlock();
        });
        threadA.start();
        threadB.start();
    }


    /**
     * Synchronized
     */
    static class PrintTest05 {
        private final int loopNum;
        private boolean flag;

        public PrintTest05(int loopNum, boolean flag) {
            this.loopNum = loopNum;
            this.flag = flag;
        }

        public synchronized void turnPrint(String str, boolean currFlag, boolean nextFlag) {
            for (int i = 0; i < loopNum; i++) {
                while (flag != currFlag) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println(str);
                flag = nextFlag;
                this.notifyAll();
            }
        }
    }


    /**
     * CyclicBarrier
     */
    public static void printTest06(){
        CyclicBarrier barrier = new CyclicBarrier(2, () -> {
            System.out.println("+++++++++++++++++++++++++++++");
        });

        Thread threadA = new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                System.out.println("A");
                try {
                    //通知到达屏障
                    barrier.await();
                    //阻塞
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread threadB = new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                try {
                    //阻塞当前线程，先输出A
                    barrier.await();
                    System.out.println("B");
                    //通知到达屏障
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        threadA.start();
        threadB.start();
    }

}
