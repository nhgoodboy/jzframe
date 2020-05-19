package com.musikouyi.learning.thread;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(3, true);

        for (int i = 0; i < 10; i++) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();//获取信号灯许可
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    System.out.println("Thread " + Thread.currentThread().getName() + " 进入" + "当前系统的并发数是：" + (3 - semaphore.availablePermits()));
                    try {
                        Thread.sleep(new Random().nextInt(1000));
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    System.out.println("Thread " + Thread.currentThread().getName() + " 即将离开");
                    semaphore.release();//释放信号灯
                    System.out.println("Thread " + Thread.currentThread().getName() + " 已经离开，当前系统的并发数是：" + (3 - semaphore.availablePermits()));
                }
            };
            pool.execute(runnable);
        }
    }
}