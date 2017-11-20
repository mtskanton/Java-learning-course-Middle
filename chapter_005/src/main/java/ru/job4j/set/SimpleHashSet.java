package ru.job4j.set;

import java.util.Objects;

public class SimpleHashSet<E> {
    int capacity = 16;
    Entry[] table = new Entry[capacity];

    boolean add(E value) {
        Entry<E> entry = new Entry(value);
        int index = entry.hash & (capacity - 1);
        if (table[index] != null) {
            return false;
        }
        table[index] = entry;
        return true;
    }

    boolean contains(E value) {
        int index = value.hashCode() & (capacity - 1);
        Entry<E> e = table[index];
        if (e != null && (e.value.equals(value))) {
            return true;
        }
        return false;
    }

    boolean remove(E value) {
        if (this.contains(value)) {
            int index = value.hashCode() & (capacity - 1);
            table[index] = null;
            return true;
        }
        return false;
    }
}
