package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;

    private int size;

    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    private void grow() {
        if (container.length == 0) {
            this.container = Arrays.copyOf(this.container, 2);
        }
        this.container = Arrays.copyOf(this.container, this.container.length * 2);
    }

    @Override
    public void add(T value) {
        if (size >= container.length) {
            grow();
        }
        this.container[size++] = value;
        this.modCount++;
    }
        @Override
        public T set(int index, T newValue) {
            T prev = get(index);
            container[index] = newValue;
            return prev;
        }

    @Override
    public T remove(int index) {
        T removes = get(index);
        System.arraycopy(container, index + 1, container, index, size - index - 1);
        container[size - 1] = null;
        size--;
        modCount++;
        return removes;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private final int expectedModCount = modCount;
            private int count = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return count < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[count++];
            }
        };
    }
}