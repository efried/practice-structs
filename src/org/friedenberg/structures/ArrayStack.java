package org.friedenberg.structures;

import org.friedenberg.structures.interfaces.Stack;

/**
 * Created by evanfriedenberg on 5/24/17.
 */
public class ArrayStack<E> implements Stack<E> {

    public static final int CAPACITY = 1000;
    private E[] data;
    private int t = -1;

    public ArrayStack(){
        this(CAPACITY);
    }

    public ArrayStack(int capacity){
        data = (E[]) new Object[capacity];
    }

    public int size() {
        return t + 1;
    }

    public boolean isEmpty() {
        return t == -1;
    }

    public E top() {
        if(isEmpty())
            return null;
        return data[t];
    }

    public E pop() {
        if(isEmpty())
            return null;
        E top = top();
        data[t] = null;
        t--;
        return top;
    }

    public void push(E e) throws IllegalStateException {
        if(size() == data.length){ throw new IllegalStateException(); }
        data[++t] = e;
    }
}
