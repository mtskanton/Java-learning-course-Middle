package ru.job4j.map;

class Entry<K, V> {

    int hash;
    K key;
    V value;

    Entry(int hash, K key, V value) {
        this.hash = hash;
        this.key = key;
        this.value = value;
    }
}