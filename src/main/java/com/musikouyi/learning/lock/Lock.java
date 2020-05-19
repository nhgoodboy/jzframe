package com.musikouyi.learning.lock;

public class Lock {

    private boolean isLock = false;
    Thread lockedBy = null;
    int lockedCount = 0;

    public synchronized void lock() throws InterruptedException {
        Thread callingThread = Thread.currentThread();
        while (isLock && lockedBy != callingThread) {
            wait();
        }
        isLock = true;
        lockedCount++;
        lockedBy = callingThread;
    }

    public synchronized void unlock() {
        if (Thread.currentThread() == this.lockedBy) {
            lockedCount--;

            if (lockedCount == 0) {
                isLock = false;
                notify();
            }
        }
    }

}
