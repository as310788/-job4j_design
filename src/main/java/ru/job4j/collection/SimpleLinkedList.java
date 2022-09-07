package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements LinkedList<E> {

    private int modCount;
    private int size;
    private Node<E> firstNode;
    private Node<E> lastNode;

    private static class Node<E> {
        E item;
        Node<E> nextNode;

        Node(E element, Node<E> nextNode) {
            this.item = element;
            this.nextNode = nextNode;
        }
    }

    @Override
    public void add(E e) {
        final Node<E> l = lastNode;
        Node newNode = new Node<>(e, null);
        lastNode = newNode;
        if (l == null) {
            firstNode = newNode;
        } else {
            l.nextNode = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> rsl = firstNode;
        for (int i = 1; i <= index; i++) {
            rsl = rsl.nextNode;
        }
        return  rsl.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private final int expectedModCount = modCount;
            Node<E> point = firstNode;
            E value;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return point != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                value = point.item;
                point = point.nextNode;
                return value;
            }
        };
    }
}
