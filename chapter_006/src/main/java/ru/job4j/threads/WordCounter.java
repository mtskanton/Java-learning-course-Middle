package ru.job4j.threads;

public class WordCounter implements Runnable {
    private String[] str;

    WordCounter(String str) {
        this.str = str.split(" +");
    }

    public void run() {
        int words = 0;
        for (String word : str) {
            words = words + 1;
        }
        System.out.println("words: " + words);
    }
}
