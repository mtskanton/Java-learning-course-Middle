package ru.job4j.jcip;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class Counter implements Runnable {

    @GuardedBy("this")
    private int sum = 0;

    public synchronized int increment() {
        for (int i = 0; i < 100000; i++) {
            sum = sum + 1;
        }
        return sum;
    }

    public void run() {
        this.increment();
    }
}
