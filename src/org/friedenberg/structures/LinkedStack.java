package org.friedenberg.structures;

import org.friedenberg.structures.interfaces.Stack;

/**
 * Created by evanfriedenberg on 5/24/17.
 */
public class LinkedStack<E> implements Stack<E> {

    private SinglyLinkedList<E> list= new SinglyLinkedList<>();

    public int size() {
        return list.getSize();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public E top() {
        return list.first();
    }

    public E pop() {
        return list.removeFirst();
    }

    public void push(E e) {
        list.addFirst(e);
    }
}
