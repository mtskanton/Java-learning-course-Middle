package ru.job4j.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import net.jcip.annotations.ThreadSafe;
import net.jcip.annotations.GuardedBy;

@ThreadSafe
public class SynchronizedSimpleArrayList<T> implements Iterable<T> {

    @GuardedBy("this")
    private Object[] container = new Object[10];
    private int size = 0;

    public synchronized void add(T value) {
        if (container.length == size) {
            container = Arrays.copyOf(container, size * 2);
        }
        container[size++] = value;
    }

    public synchronized T get(int index) {
        return (T) container[index];
    }

    @Override
    public synchronized Iterator<T> iterator() {
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
