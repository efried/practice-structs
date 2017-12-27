package org.friedenberg.structures;

import java.util.Comparator;

/**
 * Created by evanfriedenberg on 6/4/17.
 */
public class DefaultComparator<E> implements Comparator<E> {
    @Override
    public int compare(E a, E b) throws ClassCastException {
        return ((Comparable<E>) a).compareTo(b);
    }
}
