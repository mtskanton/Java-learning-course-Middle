package ru.job4j.set;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleSet<E> implements Iterable<E> {
    private Object[] container = new Object[0];
    int size = 0;
    int index = 0;

    public void add(E value) {
        boolean duplicate = false;
        for (int i = 0; i < size; i++) {
            if (container[i].equals(value)) {
                duplicate = true;
                break;
            }
        }

        if (!duplicate) {
            Object[] array = Arrays.copyOf(container, size + 1);
            array[size++] = value;
            container = array;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

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
