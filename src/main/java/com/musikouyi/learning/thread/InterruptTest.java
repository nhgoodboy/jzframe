package com.musikouyi.learning.thread;

public class InterruptTest {

    public static void main(String[] args) {
        Thread a = new Thread(()->{
            for (int i = 0; i < 100; i++) {
                System.out.println(i);
            }
            try {
                Thread.sleep(5000L);
                System.out.println("finish");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        a.start();
        a.interrupt();
    }
}
