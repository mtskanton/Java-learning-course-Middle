package ru.job4j.interrupt;

import com.sun.org.apache.xpath.internal.SourceTree;

public class Program {
    String text;
    long timer;

    Program(String text, long timer) {
        this.text = text;
        this.timer = timer;
        this.init();
    }

    private void init() {
        //Set time for the program, launch timer
        Time time = new Time(this.timer);

        //Launch thread for counting of chars
        CountChar count = new CountChar(this.text);

        //interrupt counting if timer is over
        try {
            time.thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count.thread.interrupt();
        System.out.println("Program is over");
    }

    public static void main(String[] args)  {
        Program p = new Program("Thread in Java can be interrupted", 0L);
    }
}
