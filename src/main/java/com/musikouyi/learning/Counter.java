package com.musikouyi.learning;


public class Counter {

    private Lock lock = new Lock();
    private int count = 0;

    public int inc() throws InterruptedException {
        lock.lock();
        int newCount = ++count;
        lock.unlock();
        return newCount;
    }
}
