package ru.job4j.threadpool;

/**
 * Class of work that will be picked up by the Threads out of the Pool.
 */
public class Work {

    public void fulfill() {
        try {
            Thread.sleep(2000);
            System.out.println("Task is completed by " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            System.out.println(String.format("Work performed by %s has been interrupted", Thread.currentThread().getName()));
        }
    }
}
