package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayImpl implements Array {
    private int arraySize;
    private int currentObjectCount = 0;
    protected Object[] elementData;

    public ArrayImpl(int arraySize) {
        this.arraySize = arraySize;
        elementData = new Object[arraySize];
    }

    @Override
    public void clear() {
        this.arraySize = 0;
        currentObjectCount = 0;
    }

    @Override
    public int size() {
        return arraySize;
    }

    @Override
    public Iterator<Object> iterator() {

        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {
        int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < currentObjectCount;
        }

        @Override
        public Object next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Next element not exist");
            }
            return elementData[currentIndex++];
        }
    }

    @Override
    public void add(Object element) {
        if (currentObjectCount == arraySize) {
            int newSize = arraySize + 1;
            Object[] newData = new Object[newSize];
            for (int i = 0; i < arraySize; i++) {
                newData[i] = elementData[i];
            }
            arraySize = newSize;
            elementData = new Object[arraySize];
            for (int i = 0; i < arraySize; i++) {
                elementData[i] = newData[i];
            }
        }
        elementData[currentObjectCount] = element;
        currentObjectCount++;
    }

    @Override
    public void set(int index, Object element) {
        elementData[index] = element;
    }

    @Override
    public Object get(int index) {
        return elementData[index];
    }

    @Override
    public int indexOf(Object element) {
        for (int i = 0; i < currentObjectCount; i++) {
            if (elementData[i] == element) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void remove(int index) {
        for (int i = 0; i < currentObjectCount - 1; i++) {
            if (i >= index) {
                elementData[i] = elementData[i + 1];
            }
        }
        currentObjectCount--;
        arraySize--;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < currentObjectCount; i++) {
            if (i == currentObjectCount - 1) {
                sb.append(elementData[i]);
            } else sb.append(elementData[i]).append(", ");

        }
        return "[" + sb.toString().trim() + "]";
    }

    public static void main(String[] args) {

    }

}
