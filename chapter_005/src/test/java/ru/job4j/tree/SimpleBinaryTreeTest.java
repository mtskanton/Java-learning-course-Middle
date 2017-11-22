package ru.job4j.tree;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleBinaryTreeTest {

    @Test
    public void whenAddTheSmallestElementThenAtTheLeftSideOfTree() {
        SimpleBinaryTree<Integer> sbt = new SimpleBinaryTree<>(8);
        sbt.add(3);
        assertThat(sbt.root.left.value, is(3));
        sbt.add(10);
        assertThat(sbt.root.right.value, is(10));
        sbt.add(1);
        assertThat(sbt.root.left.left.value, is(1));
        sbt.add(-11);
        assertThat(sbt.root.left.left.left.value, is(-11));
        sbt.add(6);
        assertThat(sbt.root.left.right.value, is(6));
    }
}