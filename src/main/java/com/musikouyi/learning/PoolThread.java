package com.musikouyi.learning;

public class PoolThread implements Runnable {

    private static int a = 0;

    public static void main(String[] args) {
        PoolThread thread1 = new PoolThread();
        Thread thread = new Thread(thread1);
        Thread thread2 = new Thread(thread1);
        thread.start();
        thread2.start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            a++;
            System.out.println(a);
        }
    }
}
