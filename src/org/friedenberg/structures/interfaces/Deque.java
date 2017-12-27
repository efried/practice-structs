package org.friedenberg.structures.interfaces;

/**
 * Created by evanfriedenberg on 5/29/17.
 */
public interface Deque<E> {

    int size();

    boolean isEmpty();

    E first();

    E last();

    void addFirst();

    void addLast();

    E removeFirst();

    E removeLast();

}
