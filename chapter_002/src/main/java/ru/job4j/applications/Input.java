package ru.job4j.applications;

/**
 * Интерфейс ввода данных.
 */
public interface Input {
    public String showMenu();
    public String ask(String question);
    public int ask(String question, int[] range);
}
