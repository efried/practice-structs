package org.friedenberg.structures;

import org.friedenberg.structures.interfaces.Entry;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by evanfriedenberg on 6/18/17.
 */
public abstract class AbstractHashMap<K,V> extends AbstractMap<K,V> {
    protected int n = 0;
    protected int capacity;
    private int prime;
    private long scale, shift;

    public AbstractHashMap(int capacity, int prime) {
        this.capacity = capacity;
        this.prime = prime;
        Random random = new Random();
        scale = random.nextInt(prime - 1) + 1;
        shift = random.nextInt(prime);
        createTable();
    }

    public AbstractHashMap(int capacity) {
        this(capacity, 109345121);
    }

    public AbstractHashMap() {
        this(17);
    }

    public int size() {
        return n;
    }

    public V get(K key) {
        return bucketGet(hashValue(key), key);
    }

    public V put(K key, V value) {
        V answer = bucketPut(hashValue(key), key, value);
        if (n > capacity / 2) {
            resize(2 * capacity - 1);
        }
        return answer;
    }

    private void resize(int newCap) {
        ArrayList<Entry<K,V>> buffer = new ArrayList<>(n);
        for (Entry<K, V> entry : entrySet()) {
            buffer.add(entry);
        }
        capacity = newCap;
        createTable();
        n = 0;
        for (Entry<K, V> entry : buffer) {
            put(entry.getKey(), entry.getValue());
        }
    }

    public V remove(K key) {
        return bucketRemove(hashValue(key), key);
    }

    private int hashValue(K key) {
        return (int) ((Math.abs(key.hashCode() * scale + shift) % prime) % capacity);
    }

    protected abstract void createTable();
    protected abstract V bucketGet(int h, K k);
    protected abstract V bucketPut(int h, K k, V v);
    protected abstract V bucketRemove(int h, K k);
}
