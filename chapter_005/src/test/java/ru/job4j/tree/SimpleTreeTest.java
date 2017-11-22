package ru.job4j.tree;

import org.junit.Test;
import java.util.Iterator;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleTreeTest {

    @Test
    public void whenAddItemThenItInTree() {
        SimpleTree<String> st = new SimpleTree<>("1.");
        assertThat(st.add("1.", "1.1."), is(true));
        assertThat(st.add("1.", "1.2."), is(true));
        assertThat(st.add("1.1.", "1.1.1."), is(true));
        assertThat(st.add("9.", "99."), is(false));

        boolean result = false;
        for (String v : st) {
            if (v.equals("1.1.1.")) {
                result = true;
            }
        }
        assertThat(result, is(true));

    }

    @Test
    public void whenAddItemThenNoDuplicatesInTree() {
        SimpleTree<String> st = new SimpleTree<>("1.");
        assertThat(st.add("1.", "1.1."), is(true));
        assertThat(st.add("1.", "1.2."), is(true));
        assertThat(st.add("1.1.", "1.1.1."), is(true));
        assertThat(st.add("1.1.", "1.2."), is(false));
    }

    @Test
    public void whenTreeIsBinaryThenTrueElseFalse() {
        SimpleTree<String> st = new SimpleTree<>("1.");
        assertThat(st.add("1.", "1.1."), is(true));
        assertThat(st.add("1.", "1.2."), is(true));
        assertThat(st.isBinary(), is(true));
        assertThat(st.add("1.1.", "1.1.1."), is(true));
        assertThat(st.add("1.1.", "1.1.2."), is(true));
        assertThat(st.isBinary(), is(true));
        assertThat(st.add("1.1.", "1.1.3."), is(true));
        assertThat(st.isBinary(), is(false));
    }
}