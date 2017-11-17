package ru.job4j.list;

import java.util.Arrays;
import java.util.Iterator;

public class SimpleArrayList<T> implements Iterable<T> {

    private int size = 0;
    private int index = 0;
    private Object[] container = new Object[0];

    public void add(T value) {
        Object[] array = Arrays.copyOf(container, size + 1);
        array[size++] = value;
        container = array;
    }

    public T get(int index) {
        return (T) container[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            public boolean hasNext() {
                return size < container.length;
            }

            public T next() {
                return (T) container[index++];
            }
        };
    }
}
