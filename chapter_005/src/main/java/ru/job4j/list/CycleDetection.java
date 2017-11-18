package ru.job4j.list;

/**
 * Алгоритм поиска циклов Флойда.
 *
 */

public class CycleDetection<T> {

    boolean hasCycle(Node first) {
        Node<T> slow = first;
        Node<T> fast = first;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
}

class Node<T> {
    private T value;
    Node<T> next;

    Node(T value) {
        this.value = value;
    }
}
