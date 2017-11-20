package ru.job4j.map.collisions;

public class SimpleHashMap<K, V> {
    int capacity = 16;
    Entry<K, V>[] table = new Entry[capacity];

    boolean insert(K key, V value) {
        int hash = this.hash(key);
        int index = indexFor(hash, capacity);

        Entry<K, V> found = findEntry(key, index);
        if (found != null) {
            found.value = value;
        } else {
            Entry<K, V> e = table[index];
            table[index] = new Entry<K, V>(hash, key, value, e);
        }
        return true;
    }

    V get(K key) {
        V result = null;
        int hash = this.hash(key);
        int index = indexFor(hash, capacity);

        Entry<K, V> found = findEntry(key, index);
        if (found != null) {
            result = found.value;
        }
        return result;
    }

    boolean delete(K key) {
        boolean result = false;
        int hash = hash(key);
        int index = indexFor(hash, capacity);

        Entry<K, V> found = findEntry(key, index);
        if (found != null) {
            table[index] = found.next;
            result = true;
        }

        return result;
    }

    private Entry<K, V> findEntry(K key, int index) {
        int hash = hash(key);
        Entry<K, V> result = null;
        for (Entry<K, V> e = table[index]; e != null; e = e.next) {
            if (hash(key) == e.hash && (key == e.key || key.equals(e.key))) {
                result = e;
                break;
            }
        }
        return result;
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
}
