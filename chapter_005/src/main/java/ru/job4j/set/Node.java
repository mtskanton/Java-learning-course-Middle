package ru.job4j.set;

class Node<E> {
    Node<E> prev;
    Node<E> next;
    E value;

    Node(Node<E> prev, E value, Node<E> next) {
        this.prev = prev;
        this.value = value;
        this.next = next;
    }
}