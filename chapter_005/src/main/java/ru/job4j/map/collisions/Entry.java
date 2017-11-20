package ru.job4j.map.collisions;

class Entry<K, V> {

    int hash;
    K key;
    V value;
    Entry<K, V> next;

    Entry(int hash, K key, V value, Entry<K, V> next) {
        this.hash = hash;
        this.key = key;
        this.value = value;
        this.next = next;
    }
}