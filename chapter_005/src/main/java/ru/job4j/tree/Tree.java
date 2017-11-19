package ru.job4j.tree;

public interface Tree<E extends Comparable<E>> extends Iterable<E> {

    boolean add(E parent, E child);
}
