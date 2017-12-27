package org.friedenberg.structures.interfaces;

/**
 * Created by evanfriedenberg on 5/24/17.
 */
public interface Stack<E> {

    int size();

    boolean isEmpty();

    E top();

    E pop();

    void push(E e);

}
