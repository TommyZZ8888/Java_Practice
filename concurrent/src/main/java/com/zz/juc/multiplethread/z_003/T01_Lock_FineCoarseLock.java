package com.zz.juc.multiplethread.z_003;

/**
 * @Description T01_Lock_fineCoarseLock
 * @Author 张卫刚
 * @Date Created on 2023/7/5
 */
public class T01_Lock_FineCoarseLock {
    int count = 0;

    synchronized void m() {
        //do something async

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //业务逻辑中，只有这一句需要sync，就不应该给整个方法上锁
        count++;
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void m2() {
        //do something need not sync
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //采用细粒度的锁，可以缩短线程争用时间，提高效率
        synchronized (this) {
            count++;
        }

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
