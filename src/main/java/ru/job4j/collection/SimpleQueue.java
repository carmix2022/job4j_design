package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int size = 0;

    public T poll() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        swapStacks(in, out);
        T tmp = in.pop();
        swapStacks(out, in);
        size--;
        return tmp;
    }

    private void swapStacks(SimpleStack<T> one, SimpleStack<T> two) {
        for (int tmpSize = 0; tmpSize < size - 1; tmpSize++) {
            two.push(one.pop());
        }
    }

    public void push(T value) {
        in.push(value);
        size++;
    }
}