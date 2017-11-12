package ru.job4j.coins;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class CoffeeMachineTest {

    @Test
    public void whenValue50Price31ThenCorrectChange() {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        int[] change = coffeeMachine.changes(50, 31);
        assertThat(change.length, is(4));
        assertThat(change[0], is(10));
        assertThat(change[1], is(5));
        assertThat(change[2], is(2));
        assertThat(change[3], is(2));
    }
}
