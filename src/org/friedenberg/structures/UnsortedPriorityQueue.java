package org.friedenberg.structures;

import org.friedenberg.structures.interfaces.Entry;
import org.friedenberg.structures.interfaces.Position;
import org.friedenberg.structures.interfaces.PositionalList;

import java.util.Comparator;

/**
 * Created by evanfriedenberg on 6/4/17.
 */
public class UnsortedPriorityQueue<K,V> extends AbstractPriorityQueue<K,V> {
    private PositionalList<Entry<K,V>> list = new LinkedPositionalList<Entry<K,V>>();

    public UnsortedPriorityQueue(){
        super();
    }

    public UnsortedPriorityQueue(Comparator<K> comp){
        super(comp);
    }

    private Position<Entry<K,V>> findMin(){
        Position<Entry<K,V>> small = list.first();
        for (Position<Entry<K, V>> walk : list.positions()) {
            if(compare(walk.getElement(), small.getElement()) < 0)
                small = walk;
        }
        return small;
    }

    @Override
    public int size() {
        return size();
    }

    @Override
    public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
        checkKey(key);
        Entry<K,V> newest = new PQEntry<>(key, value);
        list.addLast(newest);
        return newest;
    }

    @Override
    public Entry<K, V> min() {
        if(isEmpty()) { return null; }
        return findMin().getElement();
    }

    @Override
    public Entry<K, V> removeMin() {
        if(isEmpty()) { return null; }
        return list.remove(findMin());
    }
}
