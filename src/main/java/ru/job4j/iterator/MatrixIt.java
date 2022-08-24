package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        while (column < data.length && row == data[column].length) {
            column++;
            row = 0;
        }
        return column < data.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[column][row++];
    }

    public static void main(String[] args) {
        int[][] in = {
                {1}, {2, 3}, {}, {}, {4}
        };
        Iterator<Integer> iterator = new MatrixIt(in);
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}