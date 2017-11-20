package ru.job4j.set;

class Entry<E> {

    int hash;
    E value;

    Entry(E value) {
        this.hash = value.hashCode();
        this.value = value;
    }
}