package ru.job4j.tree;

public class SimpleBinaryTree<E extends Comparable<E>> {
    Node<E> root;

    SimpleBinaryTree(E value) {
        root = new Node<E>(value);
    }

    //класс единицы элемента
    class Node<E> {
        E value;
        Node<E> left;
        Node<E> right;

        Node(E value) {
            this.value = value;
        }
    }

    public void add(E e) {
        Node<E> current = root;
        addRecursively(root, e);
    }

    private void addRecursively(Node<E> current, E e) {
        if (e.compareTo(current.value) <= 0) {

            if (current.left == null) {
                current.left = new Node(e);
            } else {
                addRecursively(current.left, e);
            }

        } else {

            if (current.right == null) {
                current.right = new Node(e);
            } else {
                addRecursively(current.right, e);
            }
        }
    }
}
