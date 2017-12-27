package org.friedenberg.structures.interfaces;

/**
 * Created by evanfriedenberg on 6/17/17.
 */
public interface AdaptablePriorityQueue<K,V> extends PriorityQueue<K,V>{
    void remove(Entry<K,V> entry) throws IllegalArgumentException;
    void replaceKey(Entry<K,V> entry, K key) throws IllegalArgumentException;
    void replaceValue(Entry<K,V> entry, V value) throws IllegalArgumentException;
}
