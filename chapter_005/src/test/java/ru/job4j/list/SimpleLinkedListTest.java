package ru.job4j.list;

import org.junit.Test;
import java.util.Iterator;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleLinkedListTest {

    @Test
    public void whenAddItemThenCanGetItFromTheList() {
        SimpleLinkedList<Integer> sll = new SimpleLinkedList<>();
        sll.add(1);
        sll.add(2);
        sll.add(3);
        int result = sll.get(0);
        assertThat(result, is(1));
    }

    @Test
    public void whenAddItemsThenCanIterateThem() {
        SimpleLinkedList<Integer> sll = new SimpleLinkedList<>();
        sll.add(1);
        sll.add(2);
        sll.add(3);
        Iterator<Integer> iterator = sll.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(1));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.next(), is(3));
        assertThat(iterator.hasNext(), is(false));
    }

}