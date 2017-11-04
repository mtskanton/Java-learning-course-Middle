package ru.job4j.applications;

public abstract class BaseActions implements UserAction {
    int key;
    String name;

    BaseActions(int key, String name) {
        this.key = key;
        this.name = name;
    }

    public String info() {
        return String.format("%s. %s", this.key, this.name);
    }
}
