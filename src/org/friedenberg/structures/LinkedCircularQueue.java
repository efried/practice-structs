package org.friedenberg.structures;

import org.friedenberg.structures.interfaces.CircularQueue;

/**
 * Created by evanfriedenberg on 5/24/17.
 */
public class LinkedCircularQueue<E> implements CircularQueue<E> {

    private CircularlyLinkedList<E> list = new CircularlyLinkedList<E>();

    public void enqueue(E e) {
        list.addLast(e);
    }

    public E dequeue() {
        if(isEmpty()) return null;
        return list.removeFirst();
    }

    public E first() {
        return list.first();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
    }

    public void rotate() {
        list.rotate();
    }
}
