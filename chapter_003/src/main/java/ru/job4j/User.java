package ru.job4j;

public class User {
    int id;
    String name;
    String city;

    User(int id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    public int getId() {
        return this.id;
    }
}
