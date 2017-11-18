package ru.job4j.set;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleLinkedSet<E> implements Iterable<E> {
    Node<E> first = null;
    Node<E> last = null;
    private int size = 0;
    private int index = 0;

    public void add(E value) {
        boolean duplicate = false;
        Node<E> found = first;
        for (int i = 0; i < size; i++) {
            if (found.value.equals(value)) {
                duplicate = true;
                break;
            }
            found = found.next;
        }

        if (!duplicate) {
            Node<E> item = new Node<>(last, value, null);
            if (first == null) {
                first = item;
            } else {
                last.next = item;
            }
            last = item;
            size++;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public E next() {
                if (this.hasNext()) {
                    Node<E> found = first;
                    for (int i = 0; i < index; i++) {
                        found = found.next;
                    }
                    index++;
                    return found.value;
                }
                throw new NoSuchElementException();
            }
        };
    }
}

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
