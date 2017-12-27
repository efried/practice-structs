package org.friedenberg.structures;

import org.friedenberg.structures.interfaces.Queue;

/**
 * Created by evanfriedenberg on 5/24/17.
 */
public class LinkedQueue<E> implements Queue<E> {
    private SinglyLinkedList<E> list = new SinglyLinkedList<>();

    public void enqueue(E e) {
        list.addLast(e);
    }

    public E dequeue() {
        return list.removeFirst();
    }

    public E first() {
        return list.first();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.getSize();
    }
}
