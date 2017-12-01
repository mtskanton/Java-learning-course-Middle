package ru.job4j.lock;

/**
 * SimpleLock Class provide non reentrant way of synchronization.
 * @author Anton Matsik
 * @since 01.12.2017
 */
public class SimpleLock {
    private boolean isLocked = false;

    /**
     * Locking of the thread on the current Object monitor.
     */
    public synchronized void lock() {
        while (isLocked) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        isLocked = true;
    }

    /**
     * Unlocking of the thread on the current Object monitor.
     */
    public synchronized void unlock() {
        isLocked = false;
        notify();
    }
}
