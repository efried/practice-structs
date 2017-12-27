package org.friedenberg.structures;

import org.friedenberg.structures.interfaces.Entry;
import org.friedenberg.structures.interfaces.Map;

import java.util.Iterator;

/**
 * Created by evanfriedenberg on 6/18/17.
 */
public abstract class AbstractMap<K,V> implements Map<K,V> {

    public boolean isEmpty() {
        return size() == 0;
    }

    protected static class MapEntry<K,V> implements Entry<K,V> {
        private K key;
        private V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V setValue(V value) {
            V old = this.value;
            this.value = value;
            return old;
        }
    }

    private class KeyIterator implements Iterator<K> {
        private Iterator<Entry<K,V>> iterator = entrySet().iterator();

        public boolean hasNext() {
            return iterator.hasNext();
        }

        public K next() {
            return iterator.next().getKey();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    private class KeyIterable implements Iterable<K> {
        public Iterator<K> iterator() {
            return new KeyIterator();
        }
    }
    public Iterable<K> keySet() {
        return new KeyIterable();
    }

    private class ValueIterator implements Iterator<V> {
        private Iterator<Entry<K,V>> iterator = entrySet().iterator();
        public boolean hasNext() {
            return iterator.hasNext();
        }

        public V next() {
            return iterator.next().getValue();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    private class ValueIterable implements Iterable<V> {
        public Iterator<V> iterator() {
            return new ValueIterator();
        }
    }
    public Iterable<V> values() {
        return new ValueIterable();
    }
}
