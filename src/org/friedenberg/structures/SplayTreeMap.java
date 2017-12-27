package org.friedenberg.structures;

import org.friedenberg.structures.interfaces.Entry;
import org.friedenberg.structures.interfaces.Position;

import java.util.Comparator;

public class SplayTreeMap<K,V> extends TreeMap<K,V> {

    public SplayTreeMap() { super();  }

    public SplayTreeMap(Comparator<K> comp) {
        super(comp);
    }

    private void splay(Position<Entry<K,V>> p) {
        while (!isRoot(p)) {
            Position<Entry<K, V>> parent = parent(p);
            Position<Entry<K, V>> grand = parent(parent);
            if (grand == null) {
                rotate(p);
            } else if ((parent == left(grand)) == (p == left(parent))) {
                rotate(parent);
                rotate(p);
            } else {
                rotate(p);
                rotate(p);
            }
        }
    }

    protected void rebalanceAccess(Position<Entry<K,V>> p) {
        if (isExternal(p)) {
            p = parent(p);
        }
        if (p != null) {
            splay(p);
        }
    }

    protected void rebalanceInsert(Position<Entry<K,V>> p) {
        splay(p);
    }

    protected void rebalanceDelete(Position<Entry<K,V>> p) {
        if (!isRoot(p)) {
            splay(parent(p));
        }
    }
}
