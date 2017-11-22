package ru.job4j.map;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleHashMap<K, V> implements Iterable<V> {
    int capacity = 16;
    Entry<K, V>[] table = new Entry[capacity];
    int index = 0;

    boolean insert(K key, V value) {
        int hash = this.hash(key);
        int index = indexFor(hash, capacity);
        if (table[index] != null) {
            return false;
        }
        table[index] = new Entry<>(hash, key, value);
        return true;
    }

    V get(K key) {
        int index = indexFor(hash(key), capacity);
        Entry<K, V> found = table[index];
        return found == null ? null : found.value;
    }

    boolean delete(K key) {
        int index = indexFor(hash(key), capacity);
        table[index] = null;
        return true;
    }

    //метод вычисления hash кода по аналоги вычисления в HashMap
    private int hash(K key) {
        int h = key.hashCode();
        return key == null ? 0 : h ^ (h >>> 16);
    }

    //метод вычисления индекса позиции по аналоги вычисления в HashMap
    private int indexFor(int h, int length) {
        return h & (length - 1);
    }

    @Override
    public Iterator<V> iterator() {
        return new Iterator<V>() {
            @Override
            public boolean hasNext() {
                return nextIndex() >= 0;
            }

            @Override
            public V next() {
                if (hasNext()) {
                    index = nextIndex();
                    return table[index++].value;
                }
                throw new NoSuchElementException();
            }

            private int nextIndex() {
                int ind = -1;
                for (int i = index; i < capacity; i++) {
                    if (table[i] != null) {
                        ind = i;
                        break;
                    }
                }
                return ind;
            }
        };
    }
}