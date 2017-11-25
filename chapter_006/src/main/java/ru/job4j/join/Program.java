package ru.job4j.join;

public class Program {

    public static void main(String[] args) {
        Cycle cycle = new Cycle();

        System.out.println("The program is starting.");

        try {
            cycle.thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("The program has been finalized.");
    }
}
