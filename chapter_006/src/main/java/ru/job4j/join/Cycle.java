package ru.job4j.join;

public class Cycle implements Runnable {
    Thread thread;

    Cycle() {
        this.thread = new Thread(this);
        thread.start();
    }

    public void run() {
        try {
            thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("*program output after spending some time for math operation*");
    }
}
