package ru.job4j.lock;

/**
 * SimpleLock Class provide non reentrant way of synchronization.
 * @author Anton Matsik
 * @since 01.12.2017
 */
public class SimpleLock {
    //boolean if the lock is locked
    private volatile boolean isLocked = false;

    //thread that owns the lock
    Thread lockedBy;

    /**
     * Locking of the thread on the current Object monitor.
     */
    public synchronized void lock() {
        while (this.isLocked && this.lockedBy != Thread.currentThread()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.lockedBy = Thread.currentThread();
        this.isLocked = true;
    }

    /**
     * Unlocking of the thread on the current Object monitor.
     */
    public synchronized void unlock() {
        if (this.lockedBy == Thread.currentThread()) {
            isLocked = false;
            notify();
        }
    }
}
