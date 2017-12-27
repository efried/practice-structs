package org.friedenberg.structures.interfaces;

/**
 * Created by evanfriedenberg on 6/4/17.
 */
public interface PriorityQueue<K,V> {
    int size();
    boolean isEmpty();
    Entry<K,V> insert(K key, V value) throws IllegalArgumentException;
    Entry<K,V> min();
    Entry<K,V> removeMin();
}
