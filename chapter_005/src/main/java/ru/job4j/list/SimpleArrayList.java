package ru.job4j.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArrayList<T> implements Iterable<T> {

    private int size = 0;
    private Object[] container = new Object[10];

    public void add(T value) {
        if (container.length == size) {
            container = Arrays.copyOf(container, size * 2);
        }
        container[size++] = value;
    }

    public T get(int index) {
        return (T) container[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index = 0;
            public boolean hasNext() {
                return index < size;
            }

            public T next() {
                if (this.hasNext()) {
                    return (T) container[index++];
                }
                throw new NoSuchElementException();
            }
        };
    }
}
