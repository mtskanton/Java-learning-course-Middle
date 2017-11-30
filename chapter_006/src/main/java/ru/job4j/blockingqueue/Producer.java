package ru.job4j.blockingqueue;

/**
 * Producer class.
 */
public class Producer implements Runnable {

    private Stock stock;

    Producer(Stock stock) {
        this.stock = stock;
    }

    /**
     * Put certain amount of goods on the stock.
     */
    public void run() {
        for (int i = 0; i < 5; i++) {
            stock.put();
        }
    }
}