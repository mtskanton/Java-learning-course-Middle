package ru.job4j.applications;

public interface UserAction {
    int key();
    void execute(Input input, Tracker tracker);
    String info();
}
