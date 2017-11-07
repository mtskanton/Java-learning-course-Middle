package ru.job4j.sorting;

public class User implements Comparable<User> {

    private String name;
    private int age;

    User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    @Override
    public int compareTo(User o) {
        return this.age - o.getAge();
    }

    @Override
    public String toString() {
        return "User{" + "name='" + name + '\''
                + ", age=" + age
                + '}';
    }
}