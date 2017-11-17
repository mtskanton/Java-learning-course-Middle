package ru.job4j.list;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleStackTest {

    @Test
    public void whenFirstItemPushThenLastItemPoll() {
        SimpleStack<Integer> ss = new SimpleStack<>();
        ss.push(1);
        ss.push(2);
        ss.push(3);
        int result = ss.poll();
        assertThat(result, is(3));
        int result2 = ss.poll();
        assertThat(result2, is(2));
        int result3 = ss.poll();
        assertThat(result3, is(1));
    }
}