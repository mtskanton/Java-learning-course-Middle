package ru.job4j.blockingqueue;

/**
 * Consumer class.
 */
public class Consumer implements Runnable {

    private Stock stock;

    Consumer(Stock stock) {
        this.stock = stock;
    }

    /**
     * Get certain amount of goods from the stock.
     */
    public void run() {
        for (int i = 0; i < 5; i++) {
            stock.get();
        }
    }
}
