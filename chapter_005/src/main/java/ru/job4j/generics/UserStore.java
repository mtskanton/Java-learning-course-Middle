package ru.job4j.generics;

public class UserStore extends AbstractStore<User> {

    UserStore(int size) {
        super(size);
    }
}
