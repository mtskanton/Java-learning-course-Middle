package ru.job4j.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SimpleTree<E extends Comparable<E>> implements Tree<E> {
    private Node<E> root;

    SimpleTree(E rootValue) {
        root = new Node<E>(rootValue);
    }

    class Node<E> {
        E value;
        List<Node<E>> children;

        Node(E value) {
            this.value = value;
            this.children = new ArrayList<Node<E>>();
        }
    }

    @Override
    public boolean add(E parent, E child) {

        //не допускается добавление дубликатов,
        //поэтому проверяются сперва все, что есть в дереве
        Iterator<E> iterator = this.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().compareTo(child) == 0) {
                return false;
            }
        }

        return addRecursively(root, parent, child);
    }

    // служебный метод
    // рекурсия при добавлении элемента
    private boolean addRecursively(Node<E> current, E parent, E child) {

        if (current.value.compareTo(parent) == 0) {
            current.children.add(new Node(child));
            return true;
        } else {
            for (Node<E> c : current.children) {
                return addRecursively(c, parent, child);
            }
        }

        return false;
    }

    boolean isBinary() {
        return isBinaryRecursively(root);
    }

    // служебный метод
    // рекурсия при проверке на бинарность
    boolean isBinaryRecursively(Node<E> current) {
        boolean result = true;
        if (current.children.size() > 2) {
            result = false;
        } else {
            for (Node<E> c : current.children) {
                if (!isBinaryRecursively(c)) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }


    @Override
    public Iterator<E> iterator() {
        return itemsToList(root, new ArrayList<E>()).iterator();
    }

    //добавляем все элементы в список
    private List<E> itemsToList(Node<E> current, List<E> list) {
        list.add(current.value);
        for (Node<E> child : current.children) {
            itemsToList(child, list);
        }
        return list;
    }
}
