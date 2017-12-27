package org.friedenberg.structures;

import org.friedenberg.structures.interfaces.Entry;
import org.friedenberg.structures.interfaces.PriorityQueue;

import java.util.Comparator;

/**
 * Created by evanfriedenberg on 6/4/17.
 */
public abstract class AbstractPriorityQueue<K,V> implements PriorityQueue<K,V>{

    protected static class PQEntry<K,V> implements Entry<K,V> {
        private K k;
        private V v;

        public PQEntry(K key, V value) {
            this.k = key;
            this.v = value;
        }

        public K getKey() {
            return k;
        }
        public V getValue() {
            return v;
        }
        protected void setKey(K key){
            this.k = key;
        }
        protected void setValue(V value){
            this.v = value;
        }
    }

    private Comparator<K> comp;

    protected AbstractPriorityQueue(Comparator<K> comp) {
        this.comp = comp;
    }
    protected AbstractPriorityQueue() { this(new DefaultComparator<K>()); }

    protected int compare(Entry<K,V> k1, Entry<K,V> k2) {
        return comp.compare(k1.getKey(), k2.getKey());
    }

    protected boolean checkKey(K key) throws IllegalArgumentException {
        try{
            return (comp.compare(key, key)) == 0;
        } catch(ClassCastException e) {
            throw new IllegalArgumentException("Incompatible Key");
        }
    }

    public boolean isEmpty() { return size() == 0; }
}
