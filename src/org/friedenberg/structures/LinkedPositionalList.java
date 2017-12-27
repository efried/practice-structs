package org.friedenberg.structures;

import javafx.geometry.Pos;
import org.friedenberg.structures.interfaces.Position;
import org.friedenberg.structures.interfaces.PositionalList;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by evanfriedenberg on 5/29/17.
 */
public class LinkedPositionalList<E> implements PositionalList<E>, Iterable<E> {

    private static class Node<E> implements Position<E>{
        private E element;
        private Node<E> prev;
        private Node<E> next;

        public Node(E element, Node<E> prev, Node<E> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }

        public E getElement() throws IllegalStateException {
            if(next==null){
                throw new IllegalStateException("Position no longer valid");
            }
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public Node<E> getPrev() {
            return prev;
        }

        public void setPrev(Node<E> prev) {
            this.prev = prev;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }
    }

    private Node<E> header;
    private Node<E> trailer;
    private int size = 0;


    public LinkedPositionalList() {
        this.header = new Node<>(null, null, null);
        this.trailer = new Node<>(null, header,null);
        header.setNext(trailer);
    }

    private Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if(!(p instanceof Node)){
            throw new IllegalArgumentException("Invalid p");
        }
        Node<E> node = (Node<E>) p;
        if(node.getNext() == null){
            throw new IllegalArgumentException("p is no longer in the list");
        }
        return node;
    }

    private Position<E> position(Node<E> node){
        if(node == header || node == trailer)
            return null;
        return node;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size==0;
    }

    public Position<E> first() {
        return position(header.getNext());
    }

    public Position<E> last() {
        return position(trailer.getPrev());
    }

    public Position<E> before(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return position(node.getPrev());
    }

    public Position<E> after(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return position(node.getNext());
    }

    private Position<E> addBetween(E e, Node<E> pred, Node<E> succ){
        Node<E> newest = new Node<>(e, pred, succ);
        pred.setNext(newest);
        succ.setPrev(newest);
        size++;
        return newest;
    }

    public Position<E> addFirst(E e) {
        return addBetween(e, header, header.getNext());
    }

    public Position<E> addLast(E e) {
        return addBetween(e, trailer.getPrev(), trailer);
    }

    public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return addBetween(e, node.getPrev(), node);
    }

    public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return addBetween(e, node, node.getNext());
    }

    public E set(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        E answer = node.getElement();
        node.setElement(e);
        return answer;
    }

    public E remove(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        Node<E> predecessor = node.getPrev();
        Node<E> successor = node.getNext();
        predecessor.setNext(successor);
        successor.setPrev(predecessor);
        size--;
        E answer = node.getElement();
        node.setElement(null);
        node.setNext(null);
        node.setPrev(null);
        return answer;
    }

    public Iterator<E> iterator() {
        return new ElementIterator();
    }

    private class PositionIterator implements Iterator<Position<E>> {
        private Position<E> cursor = first();
        private Position<E> recent = null;

        @Override
        public boolean hasNext() {
            return (cursor!=null);
        }

        @Override
        public Position<E> next() throws NoSuchElementException{
            if (cursor == null) throw new NoSuchElementException("nothing left");
            recent = cursor;
            cursor = after(cursor);
            return recent;
        }

        @Override
        public void remove() throws IllegalStateException{
            if(recent == null) throw new IllegalStateException("nothing to remove");
            LinkedPositionalList.this.remove(recent);
            recent=null;
        }
    }

    private class PositionIterable implements Iterable<Position<E>>{
        @Override
        public Iterator<Position<E>> iterator(){
            return new PositionIterator();
        }
    }

    public Iterable<Position<E>> positions(){
        return new PositionIterable();
    }

    private class ElementIterator implements Iterator<E> {
        Iterator<Position<E>> posIterator = new PositionIterator();
        @Override
        public boolean hasNext() {
            return posIterator.hasNext();
        }

        @Override
        public E next() {
            return posIterator.next().getElement();
        }

        @Override
        public void remove() {
            posIterator.remove();
        }
    }

}