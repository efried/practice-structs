package org.friedenberg.structures;

import org.friedenberg.structures.interfaces.Entry;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by evanfriedenberg on 6/18/17.
 */
public class UnsortedTableMap<K,V> extends AbstractMap<K,V> {
    private ArrayList<MapEntry<K,V>> table = new ArrayList<>();

    public UnsortedTableMap() {
    }

    protected int findIndex(K key) {
        for (int j = 0; j < table.size(); j++) {
            if(table.get(j).getKey().equals(key)) {
                return j;
            }
        }
        return -1;
    }
    public int size() {
        return table.size();
    }

    public V get(K key) {
        int j = findIndex(key);
        if (j == -1) {
            return null;
        } else {
            return table.get(j).getValue();
        }
    }

    public V put(K key, V value) {
        int j = findIndex(key);
        if(j == -1) {
            table.add(new MapEntry<>(key, value));
            return null;
        } else {
            return table.get(j).setValue(value);
        }
    }

    public V remove(K key) {
        int j = findIndex(key);
        int n = table.size();
        if(j == -1) {
            return null;
        }
        V answer = table.get(j).getValue();
        if(j != n - 1) {
            table.set(j, table.get(n - 1));
        }
        table.remove(n - 1);
        return answer;

    }

    protected class EntryIteror implements Iterator<Entry<K,V>> {
        private int j = 0;
        public boolean hasNext() {
            return j < table.size();
        }

        public Entry<K, V> next() {
            if(j == table.size()) {
                throw new NoSuchElementException();
            }
            return table.get(j++);
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    protected class EntryIterable implements Iterable<Entry<K,V>> {
        public Iterator<Entry<K, V>> iterator() {
            return new EntryIteror();
        }
    }

    public Iterable<Entry<K, V>> entrySet() {
        return new EntryIterable();
    }
}
