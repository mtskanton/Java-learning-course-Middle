package ru.job4j.set;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleLinkedSet<E> implements Iterable<E> {
    Node<E> first = null;
    Node<E> last = null;
    private int size = 0;

    public void add(E value) {
        if (!duplicate(value)) {
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

    private boolean duplicate(E v) {
        Node<E> found = first;
        for (int i = 0; i < size; i++) {
            if (found.value.equals(v)) {
                return true;
            }
            found = found.next;
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int index = 0;

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
