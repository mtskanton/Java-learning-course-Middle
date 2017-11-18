package ru.job4j.set;

import org.junit.Test;
import java.util.Iterator;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleLinkedSetTest {

    @Test
    public void whenAddItemThenHasItInSet() {
        SimpleLinkedSet<Integer> ss = new SimpleLinkedSet<>();
        Iterator<Integer> iterator = ss.iterator();
        ss.add(1);
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(1));
        assertThat(iterator.hasNext(), is(false));
    }

    @Test
    public void whenAddItemsThenNoDuplicates() {
        SimpleLinkedSet<Integer> ss = new SimpleLinkedSet<>();
        ss.add(1);
        ss.add(2);
        ss.add(2);
        ss.add(3);
        Iterator<Integer> iterator = ss.iterator();
        assertThat(iterator.next(), is(1));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.next(), is(3));
        assertThat(iterator.hasNext(), is(false));
    }

}