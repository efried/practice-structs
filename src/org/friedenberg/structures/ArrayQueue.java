package org.friedenberg.structures;

import org.friedenberg.structures.interfaces.Queue;

/**
 * Created by evanfriedenberg on 5/24/17.
 */
public class ArrayQueue<E> implements Queue<E> {
    private static final int CAPACITY = 1000;
    private E[] data;
    private int f;
    private int sz;

    public ArrayQueue(){
        this(CAPACITY);
    }

    public ArrayQueue(int capacity){
        data = (E[]) new Object[capacity];
    }


    public void enqueue(E e) throws IllegalStateException{
        if(sz == data.length) throw new IllegalStateException();
        int location = (f + sz) % data.length;
        data[location] = e;
        sz++;
    }

    public E dequeue() {
        if(sz==0) return null;
        E removed = data[f];
        f = (f + 1) % data.length;
        sz--;
        return removed;
    }

    public E first() {
        if(isEmpty()) return null;
        return data[f];
    }

    public boolean isEmpty() {
        return sz == 0;
    }

    public int size() {
        return sz;
    }
}
