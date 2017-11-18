package ru.job4j.set;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleHashSetTest {

    @Test
    public void whenAddItemToHashSetThenContainsIt() {
        SimpleHashSet<Integer> shs = new SimpleHashSet<>();
        assertThat(shs.add(1), is(true));
        assertThat(shs.contains(1), is(true));
        assertThat(shs.remove(1), is(true));
        assertThat(shs.contains(1), is(false));
    }
}