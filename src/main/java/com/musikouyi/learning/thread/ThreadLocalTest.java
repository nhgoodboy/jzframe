package com.musikouyi.learning.thread;

public class ThreadLocalTest {

    private static class MyRunnable implements Runnable {

        public ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

        @Override
        public void run() {
            threadLocal.set((int) (Math.random() * 1000));

            try{
                Thread.sleep(2000);
            }catch (InterruptedException e){

            }
            System.out.println(threadLocal.get());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyRunnable myRunnable = new MyRunnable();

        Thread thread1 = new Thread(myRunnable);
        Thread thread2 = new Thread(myRunnable);
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }



}
