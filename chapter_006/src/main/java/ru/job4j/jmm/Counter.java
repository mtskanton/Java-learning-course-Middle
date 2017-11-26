package ru.job4j.jmm;

public class Counter implements Runnable {
    int sum = 0;

    public void run() {
        for (int i = 0; i < 100000; i++) {
            sum = sum + 1;
        }
    }
}
