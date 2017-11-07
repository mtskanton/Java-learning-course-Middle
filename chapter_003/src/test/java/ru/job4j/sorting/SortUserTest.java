package ru.job4j.sorting;

import java.util.*;
import org.junit.Test;
import ru.job4j.OutputTest;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;


public class SortUserTest extends OutputTest {
    /**
     * Test of
     */
    @Test
    public void whenSortUsersByAgeThenListOfAgeByIncreasing() {
        SortUser su = new SortUser();
        List<User> list = new ArrayList<>();
        list.addAll(Arrays.asList(
                new User("Zig", 30),
                new User("Amber", 20),
                new User("Bill", 10))
        );

        Set<User> sortedList = su.sort(list);
        System.out.print(sortedList);
        String expected = "[User{name='Bill', age=10}, User{name='Amber', age=20}, User{name='Zig', age=30}]";
        assertThat(expected, is(output.toString()));
    }
}
