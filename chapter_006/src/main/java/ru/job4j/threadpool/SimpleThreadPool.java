package ru.job4j.threadpool;

import java.util.LinkedList;

/**
 * SimpleThreadPool Class.
 * @author Anton Matsik (mailto: mtsk.anton@yandex.ru)
 * @since 01.12.2017
 */
public class SimpleThreadPool {
    //list of working tasks for fulfillment
    private final LinkedList<Work> tasks;

    //pool of threads
    private final Thread[] threads;

    //indicator of stopped ThreadPool
    private boolean stopped = false;

    SimpleThreadPool() {

        //amount of cores
        int cores = Runtime.getRuntime().availableProcessors();

        tasks = new LinkedList<>();
        threads = new Thread[cores];

        //filling of Pool with WorkingThreads
        for (int i = 0; i < cores; i++) {
            threads[i] = new WorkingThread();
        }

        //starting of the each thread
        for (Thread thread : threads) {
            thread.start();
        }
    }

    //adding of the work into the queue
    public void add(Work work) {
        synchronized (this.tasks) {
            tasks.addLast(work);
            tasks.notify();
        }
    }

    //stop the ThreadPool when necessary
    public void doStop() {
        this.stopped = true;
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }

    /**
     * Working Thread Class.
     * Each thread take the coming work out of the queue, fulfill it and get back into the pool.
     * Thread stays in awaiting condition if no work available.
     */
    public class WorkingThread extends Thread {
        public void run() {
            //continue to take work from the queue
            while (!stopped) {
                try {
                    Work work;
                    synchronized (tasks) {
                        while (tasks.isEmpty()) {
                            tasks.wait();
                        }
                        work = tasks.removeFirst();
                    }
                    //fulfillment is performed out of the synchronized block to not hold back other threads
                    work.fulfill();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //an example of usage
    public static void main(String[] args) {
        SimpleThreadPool tp = new SimpleThreadPool();
        Work work = new Work();
        for (int i = 0; i < 100; i++) {
            tp.add(work);
        }

        //interrupting the Threads after 6 seconds for demonstration goals
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tp.doStop();
    }
}
