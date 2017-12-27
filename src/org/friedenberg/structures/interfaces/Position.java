package org.friedenberg.structures.interfaces;

/**
 * Created by evanfriedenberg on 5/29/17.
 */
public interface Position<E> {
    E getElement() throws IllegalStateException;
}
