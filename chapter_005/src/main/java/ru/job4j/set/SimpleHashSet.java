package ru.job4j.set;

import java.util.Objects;

public class SimpleHashSet<E> {
    int capacity = 16;
    Entry[] table = new Entry[capacity];

    boolean add(E value) {
        Entry<E> entry = new Entry(value);
        int index = entry.hash & (capacity - 1);
        table[index] = entry;
        return table[index] != null;
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

class Entry<E> {

    int hash;
    E value;

    Entry(E value) {
        this.hash = value.hashCode();
        this.value = value;
    }
}
