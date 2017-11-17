package ru.job4j.list;

public class SimpleQueue<E> {
    Item<E> first = null;
    Item<E> last = null;

    public void push(E value) {
        Item<E> newItem = new Item<E>(last, value, null);
        if (last == null) {
            first = newItem;
        } else {
            last.next = newItem;
        }
        last = newItem;
    }

    public E poll() {
        Item<E> polled = first;
        first = first.next;
        return polled.value;
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
}
