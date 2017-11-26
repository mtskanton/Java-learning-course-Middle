package ru.job4j.interrupt;

public class Time implements Runnable {
    Thread thread;
    private long time;

    Time(long t) {
        this.thread = new Thread(this);
        this.time = t;
        thread.start();
    }

    public void run() {
        try {
            thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
