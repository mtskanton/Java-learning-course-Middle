package ru.job4j.generics;

public interface Store<T extends Base> {
    T add(T model);
    T update(T model);
    boolean delete(String id);
}