package org.friedenberg.structures.interfaces;

/**
 * Created by evanfriedenberg on 5/24/17.
 */
public interface Queue<E> {

    void enqueue(E e);

    E dequeue();

    E first();

    boolean isEmpty();

    int size();

}
