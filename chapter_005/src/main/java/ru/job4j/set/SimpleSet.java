package ru.job4j.set;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleSet<E> implements Iterable<E> {
    private Object[] container = new Object[1];
    int size = 0;

    public void add(E value) {
        if (!duplicate(value)) {
            if (container.length == size) {
                container = Arrays.copyOf(container, size * 2);
            }
            container[size++] = value;
        }
    }

    private boolean duplicate(E v) {
        for (int i = 0; i < size; i++) {
            if (container[i].equals(v)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public E next() {
                if (this.hasNext()) {
                    return (E) container[index++];
                }
                throw new NoSuchElementException();
            }
        };
    }
}
