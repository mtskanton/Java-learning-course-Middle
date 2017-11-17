package ru.job4j.generics;

public class UserStore<T extends Base> extends AbstractStore<T> {

    UserStore(int size) {
        super(size);
    }
}
