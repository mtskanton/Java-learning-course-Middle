package ru.job4j.interrupt;

public class CountChar implements Runnable {
    Thread thread;
    private char[] charArray;

    CountChar(String str) {
        this.thread = new Thread(this);
        charArray = str.toCharArray();
        thread.start();
    }

    /**
     * Counts the amount of characters in provided text.
     */
    public void run() {
        int sum = 0;
        for (char c : charArray) {
            sum = sum + 1;
            if (this.thread.isInterrupted()) {
                System.out.println("The thread has been interupted");
                break;
            }
        }
        System.out.println("Amount of chars in this text: " + sum);
    }
}
