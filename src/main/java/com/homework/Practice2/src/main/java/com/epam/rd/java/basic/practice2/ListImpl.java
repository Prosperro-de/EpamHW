package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListImpl implements List {
        private Node tail;
        private Node head;
        private int size;

        @Override
        public void clear() {
            tail = head = null;
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

            private Node currentNode = head;
            private Node removedNode;

            @Override
            public boolean hasNext() {
                return currentNode != null;
            }

            @Override
            public Object next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("Next element not exist");
                }

                removedNode = currentNode;
                Object value = currentNode.value;
                currentNode = currentNode.next;
                return value;
            }

            @Override
            public void remove() {
                if (removedNode.prev == null && removedNode.next == null) {
                    head = tail = null;
                } else if (removedNode.prev == null) {
                    head = removedNode.next;
                    removedNode.next.prev = null;
                } else if (removedNode.next == null) {
                    tail = removedNode.prev;
                    removedNode.prev.next = null;
                } else {
                    removedNode.prev.next = removedNode.next;
                    removedNode.next.prev = removedNode.prev;
                }
                size--;
            }
        }

        @Override
        public void addFirst(Object element) {
            add(element, 0);
        }

        @Override
        public void addLast(Object element) {
            add(element, size);
        }

        @Override
        public void removeFirst() {
            head = head.next;
            size--;
        }

        @Override
        public void removeLast() {
            tail = tail.prev;
            size--;
        }

        @Override
        public Object getFirst() {
            if (size == 0) {
                return null;
            }
            return head.value;
        }

        @Override
        public Object getLast() {
            if (size == 0) {
                return null;
            }
            return tail.value;
        }

        @Override
        public Object search(Object element) {
            if (element == null) {
                for (Object object : this) {
                    if (object == null) {
                        return null;
                    }
                }
            } else {
                for (Object object : this) {
                    if (element.equals(object)) {
                        return object;
                    }
                }
            }
            return null;
        }

        @Override
        public boolean remove(Object element) {
            if (size == 0) {
                return false;
            }
            Iterator iterator = this.iterator();
            while (iterator.hasNext()) {
                Object obj = iterator.next();
                if (obj == null) {
                    if (obj == element) {
                        iterator.remove();
                        return true;
                    }
                } else {
                    if (obj.equals(element)) {
                        iterator.remove();
                        return true;
                    }
                }

            }
            return false;
        }

        @Override
        public String toString() {
            int index = 0;
            StringBuilder builder = new StringBuilder();
            builder.append("[");
            for (Object obj : this) {
                builder.append(obj);
                if (index == size - 1) {
                    break;
                }
                builder.append(", ");
                index++;
            }
            builder.append("]");
            return builder.toString();
        }

        private void add(Object value, int index) {
            validateIndexForAdd(index);
            Node newNode = new Node(value);

            if (size == 0) {
                tail = head = newNode;
            } else if (index == 0) {
                head.prev = newNode;
                newNode.next = head;
                head = newNode;
            } else if (index == size) {
                tail.next = newNode;
                newNode.prev = tail;
                tail = newNode;
            } else {
                Node nodeToShift = getNode(index);
                nodeToShift.prev.next = newNode;
                newNode.prev = nodeToShift.prev;
                newNode.next = nodeToShift;
                nodeToShift.prev = newNode;
            }
            size++;
        }

        private Node getNode(int index) {
            Node node;
            if (index <= size / 2) {
                node = head;
                for (int i = 0; i < index; i++) {
                    node = node.next;
                }
            } else {
                node = tail;
                for (int i = size - 1; i > index; i--) {
                    node = node.prev;
                }
            }
            return node;
        }

        private void validateIndexForAdd(int index) {
            if (index < 0 || index > size) {
                throw new NoSuchElementException(
                        String.format("Index should be between 0 and size exclusive [ 0, %d), but was %d", size, index));
            }
        }

        private class Node {
            private Object value;
            private Node prev;
            private Node next;

            private Node(Object value) {
                this.value = value;
            }
        }

        public static void main(String[] args) {
            ListImpl list = new ListImpl();
            list.addFirst(1);
        }
    }
