package ru.job4j.textsearch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.LineNumberReader;

/**
 * Класс поиска текста в файле.
 * Запуск в потоке с возвращением результата
 */
public class TextSearch implements Runnable {

    private String findText;
    private String file;
    private ParallelSearch ps;

    TextSearch(ParallelSearch ps, String text, String file) {
        this.ps = ps;
        this.findText = text;
        this.file = file;
    }

    public void run() {
        String s;
        try {
            LineNumberReader lnr =
                    new LineNumberReader(
                            new BufferedReader(
                                    new FileReader(file)));
            while (true) {
                s = lnr.readLine();
                if (s == null) {
                    ps.decrementCounter();
                    break;
                }

                if (s.contains(findText)) {
                    ps.addResult(file);
                    break;
                }
            }

            lnr.close();

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
