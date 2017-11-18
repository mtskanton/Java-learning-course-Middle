package ru.job4j.list;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CycleDetectionTest {

    @Test
    public void whenLoopInLinkedArrayThenItIsDetected() {
        CycleDetection<Integer> cd = new CycleDetection<>();
        Node<Integer> first = new Node<>(1);
        Node<Integer> second = new Node<>(2);
        Node<Integer> third = new Node<>(3);
        Node<Integer> fourth = new Node<>(4);
        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = first;
        boolean result = cd.hasCycle(first);
        assertThat(result, is(true));
    }

    @Test
    public void whenNoLoopInLinkedArrayThenItIsDetected() {
        CycleDetection<Integer> cd = new CycleDetection<>();
        Node<Integer> first = new Node<>(1);
        Node<Integer> second = new Node<>(2);
        Node<Integer> third = new Node<>(3);
        Node<Integer> fourth = new Node<>(4);
        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = null;
        boolean result = cd.hasCycle(first);
        assertThat(result, is(false));
    }
}