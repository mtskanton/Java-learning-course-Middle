package ru.job4j.threads;

public class Counter {

    public static void main(String[] args) {
        String str = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt "
                + "ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco "
                + "laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in "
                + "voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat "
                + "non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
        Thread t1 = new Thread(new SpaceCounter(str));
        Thread t2 = new Thread(new WordCounter(str));
        t1.start();
        t2.start();
    }
}
