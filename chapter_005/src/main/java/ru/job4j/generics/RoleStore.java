package ru.job4j.generics;

public class RoleStore<T extends Base> extends AbstractStore<T> {

    RoleStore(int size) {
        super(size);
    }
}
