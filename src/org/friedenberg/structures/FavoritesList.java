package org.friedenberg.structures;

import org.friedenberg.structures.interfaces.Position;
import org.friedenberg.structures.interfaces.PositionalList;

import java.util.Iterator;

/**
 * Created by evanfriedenberg on 6/4/17.
 */
public class FavoritesList<E> {

    protected static class Item<E>{
        private E value;
        private int count;

        public Item(E value) {
            this.value = value;
        }

        public E getValue() {
            return value;
        }

        public int getCount() {
            return count;
        }


        public void increment(){
            count++;
        }
    }

    PositionalList<Item<E>> list = new LinkedPositionalList<>();
    public FavoritesList(){};

    protected E value(Position<Item<E>> p){ return p.getElement().getValue(); }

    protected int count(Position<Item<E>> p){ return p.getElement().getCount(); }

    protected Position<Item<E>> findItem(E e) {
        Position<Item<E>> walk = list.first();
        while(walk!=null && !e.equals(value(walk))){
            walk = list.after(walk);
        }
        return walk;
    }

    protected void moveUp(Position<Item<E>> p){
        int count = count(p);
        Position<Item<E>> walk = p;
        while(walk!=list.first() && count(list.before(walk)) < count){
            walk = list.before(walk);
        }
        if(walk!=p){
            list.addBefore(walk, list.remove(p));
        }
    }

    public int size(){ return list.size(); }

    public boolean isEmpty(){ return list.isEmpty(); }

    public void access(E e){
        Position<Item<E>> p = findItem(e);
        if(p==null){
            p = list.addLast(new Item<E>(e));
        }
        p.getElement().increment();
        moveUp(p);
    }

    public void remove(E e){
        Position<Item<E>> p = findItem(e);
        if(p != null){
            list.remove(p);
        }
    }

    public Iterable<E> getFavorites(int k) throws IllegalArgumentException {
        if(k < 0 || k > size()){
            throw new IllegalArgumentException("invalid k");
        }
        PositionalList<E> result = new LinkedPositionalList<>();
        Iterator<Item<E>> iter = list.iterator();
        for (int i = 0; i < k; i++) {
            result.addLast(iter.next().getValue());
        }
        return (Iterable<E>) result.iterator();
    }

}
