package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class StackImpl implements Stack {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private Object[] array;

    public StackImpl() {
        this.array = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {
        private int index = size-1;

        @Override
        public boolean hasNext() {
            return index >= 0;
        }

        @Override
        public Object next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Next element not exist");
            }
            return array[index--];
        }

    }

    @Override
    public void push(Object element) {
        ensureCapacity();
        array[size] = element;
        size++;
    }

    @Override
    public Object pop() {
        if (size == 0){
            return null;
        }
        Object object = array[size - 1];
        array[size - 1] = null;
        size--;
        return object;
    }

    @Override
    public Object top() {
        if (size == 0){
            return null;
        }
        return array[size - 1];
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            if (i == size - 1) {
                stringBuilder.append(array[size - 1]);
                break;
            }
            stringBuilder.append(array[i]);
            stringBuilder.append(", ");
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        StackImpl stack = new StackImpl();
        stack.clear();
        stack.push(1);
    }

    private void resize() {
        Object[] newArray = new Object[(int) ((size + 1.5) * 1)];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    private void ensureCapacity() {
        if (size == array.length) {
            resize();
        }
    }


}
