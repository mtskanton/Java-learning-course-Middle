package ru.job4j.blockingqueue;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Stock class. Producer class put goods, Consumer class get goods.
 */
@ThreadSafe
public class Stock {

    //amount of goods currently on the stock.
    @GuardedBy("this")
    private int goods = 0;

    /**
     * Producer puts products until total amount counts 3.
     * Waits if 3 of goods on the stock.
     */
    public synchronized void put() {
        while (goods >= 3) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        goods++;
        System.out.println(String.format("put product on the stock. Current amount: %s", goods));
        notify();
    }

    /**
     * Consumer gets products from the stock when any available.
     * Waits if no goods currently available.
     */
    public synchronized void get() {
        if (goods < 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        goods--;
        System.out.println(String.format("get product from the stock. Current amount: %s", goods));
        notify();
    }

    public static void main(String[] args) {
        Stock stock = new Stock();

        new Thread(new Producer(stock)).start();
        new Thread(new Consumer(stock)).start();
    }
}
