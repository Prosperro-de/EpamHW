package com.epam.rd.java.basic.practice2;

public class Demo {
    public static void main(String[] args) {
        ArrayImpl array = new ArrayImpl(5);
        array.add(23);
        array.add(12);
        System.out.println(array.size());

        ListImpl list = new ListImpl();
        list.addFirst(123);
        list.addFirst(411);
        System.out.println(list.size());

        StackImpl stack = new StackImpl();
        stack.push(223);
        stack.push(231);
        System.out.println(stack.top());

        QueueImpl queue = new QueueImpl();
        queue.enqueue(321);
        queue.enqueue(54321);
        System.out.println(queue.iterator().next());
    }
}
