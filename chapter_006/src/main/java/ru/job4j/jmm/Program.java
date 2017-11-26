package ru.job4j.jmm;

/**
 * Класс запускает несколько потоков, которые работают с единым ресурсом.
 * Без синхронизации доступа - некорректный результат подсчета.
 */
public class Program {

    public static void main(String[] args) {
        Counter counter = new Counter();

        Thread t1 = new Thread(counter);
        t1.start();
        Thread t2 = new Thread(counter);
        t2.start();
        Thread t3 = new Thread(counter);
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(counter.sum);
    }
}
