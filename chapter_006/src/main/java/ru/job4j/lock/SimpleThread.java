package ru.job4j.lock;

/**
 * SimpleThread Class for demonstration of SimpleLock in action.
 */
public class SimpleThread implements Runnable {
    private int count = 0;
    SimpleLock lock = new SimpleLock();

    public void run() {
        lock.lock();
        count = count + 1;
        lock.unlock();
    }

    public static void main(String[] args) {
        SimpleThread st = new SimpleThread();

        //creation of 100000 threads for counting
        for (int i = 0; i < 100000; i++) {
            Thread t = new Thread(st);
            t.start();
        }

        //5 sec to let all of the threads to complete counting
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(st.count);
    }
}
