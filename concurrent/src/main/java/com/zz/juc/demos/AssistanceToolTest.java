package com.zz.juc.demos;

import java.util.concurrent.*;

/**
 * @Describtion: AssistanceToolTest
 * @Author: 张卫刚
 * @Date: 2024/1/14 15:42
 */
public class AssistanceToolTest {
    public static void main(String[] args) throws InterruptedException {
//        countDownLatchTest();
//        cyclicBarrierTest();
//        semaphoreTest();
//        phaserTest();

        phaserDemo();
    }


    /**
     * 4个线程 4个到达 第一阶段完成
     * 第一阶段完成后，三个到达 二阶段完成
     * 两个到达，三阶段完成
     */
    public static void phaserDemo() {
        Phaser phaser = new Phaser(4);

        for (int i = 0; i < 4; i++) {
            new Thread(() -> {
                sleep(3);
              exec(0);
              arrive(phaser,0);
              phaser.awaitAdvance(0);

                sleep(3);
                exec(1);
                arrive(phaser,1);
                phaser.awaitAdvance(1);

                sleep(3);
                exec(2);
                arrive(phaser,2);
                phaser.awaitAdvance(2);


            }).start();
        }

        phaser.awaitAdvance(0);
        System.out.println("the first phase  is finished");
        phaser.arriveAndDeregister();

        phaser.awaitAdvance(1);
        System.out.println("the second phase  is finished");
        phaser.arriveAndDeregister();

        phaser.awaitAdvance(2);
        System.out.println("the third phase  is finished");
        phaser.arriveAndDeregister();
    }

    static void exec(int num){
        System.out.println(Thread.currentThread().getName()+" exec "+num);
    }

    static void arrive(Phaser phaser,int phase){
        if (phaser.getPhase()==phase){
            phaser.arrive();
        }
    }

    static void sleep(int i) {
        try {
            Thread.sleep(Double.valueOf(Math.random() * 1000*i).longValue());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * phaser 可以做到 cyclicBarrier和CountDownLatch 单个辅助工具 做不到的工作
     */
    public static void phaserTest() {
        Phaser phaser = new Phaser(5);
        getPhaserInfo(phaser);

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
//                    phaser.register();// 注册
                    Thread.sleep(Double.valueOf(Math.random() * 3000).longValue());
                    System.out.println("please choose your character,player " + Thread.currentThread().getName());
//                    phaser.arriveAndAwaitAdvance();//相当于barrier
                    phaser.arrive();//相当于countdown

//                    phaser.arriveAndDeregister(); //到达然后取消注册

//                    System.out.println(Thread.currentThread().getName() + "player finished choose");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }

        phaser.awaitAdvance(phaser.getPhase());
        System.out.println("game1 start");
        getPhaserInfo(phaser);
        phaser.awaitAdvance(phaser.getPhase());
        System.out.println("game2 start");
    }

    ;

    public static void getPhaserInfo(Phaser phaser) {
        System.out.println("phaser phase:" + phaser.getPhase());
        System.out.println("phaser ArrivedParties:" + phaser.getArrivedParties());
        System.out.println("phaser UnarrivedParties:" + phaser.getUnarrivedParties());
        System.out.println("phaser parties:" + phaser.getRegisteredParties());//总数
    }

    public static void semaphoreTest() {
        Semaphore semaphore = new Semaphore(5);

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    Thread.sleep(Double.valueOf(Math.random() * 3000).longValue());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("please choose your character,player " + Thread.currentThread().getName());
                semaphore.release();
                System.out.println(Thread.currentThread().getName() + "player finished choose");
            }).start();
        }
        System.out.println("game start");
    }

    public static void cyclicBarrierTest() {
        CyclicBarrier barrier = new CyclicBarrier(5);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(Double.valueOf(Math.random() * 3000).longValue());
                    System.out.println("please choose your character,player " + Thread.currentThread().getName());
                    barrier.await();
                    System.out.println(Thread.currentThread().getName() + "player finished choose");
                } catch (InterruptedException | BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }
        System.out.println("game start");
    }

    public static void countDownLatchTest() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(Double.valueOf(Math.random() * 3000).longValue());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("please choose your character,player " + Thread.currentThread().getName());
                countDownLatch.countDown();
            }).start();
        }

        countDownLatch.await();
        System.out.println("game start");
    }
}
