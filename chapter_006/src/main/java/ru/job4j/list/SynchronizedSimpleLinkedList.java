package ru.job4j.list;

import java.util.Iterator;
import java.util.NoSuchElementException;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class SynchronizedSimpleLinkedList<E> implements Iterable<E> {
    Item<E> first = null;
    Item<E> last = null;
    int size = 0;
    int index = 0;

    public synchronized void add(E value) {
        Item<E> newItem = new Item(last, value, null);
        if (last == null) {
            first = newItem;
        } else {
            last.next = newItem;
        }
        last = newItem;
        size++;
    }

    public synchronized E get(int index) {
        Item<E> item = first;
        for (int i = 0; i < index; i++) {
            item = item.next;
        }
        return item.value;
    }

    private class Item<E> {
        Item<E> prev;
        E value;
        Item<E> next;

        Item(Item<E> prev, E value, Item<E> next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }

    @Override
    public synchronized Iterator<E> iterator() {
        return new Iterator<E>() {

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public E next() {
                if (hasNext()) {
                    Item<E> item = first;
                    for (int i = 0; i < index; i++) {
                        item = item.next;
                    }
                    index++;
                    return item.value;
                }
                throw new NoSuchElementException();
            }
        };
    }
}
