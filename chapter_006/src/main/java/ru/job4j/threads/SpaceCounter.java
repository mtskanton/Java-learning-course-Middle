package ru.job4j.threads;

public class SpaceCounter implements Runnable {
    private char[] charArray;

    SpaceCounter(String str) {
        this.charArray = str.toCharArray();
    }

    public void run() {
        int spaces = 0;
        for (char c : charArray) {
            if (c == ' ') {
                spaces++;
            }
        }
        System.out.println("spaces: " + spaces);
    }
}
