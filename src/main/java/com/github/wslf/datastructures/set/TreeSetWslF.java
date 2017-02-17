package com.github.wslf.datastructures.set;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * @author Wsl_F
 * @param <E> type of elements in the set
 */
public class TreeSetWslF<E> extends TreeSet<E> {

    public TreeSetWslF() {
        super();
    }

    public TreeSetWslF(Comparator<? super E> comparator) {
        super(comparator);
    }

    public TreeSetWslF(Collection<? extends E> c) {
        super(c);
    }

    public TreeSetWslF(SortedSet<E> s) {
        super(s);
    }

    public ArrayList<E> getFirstN(int n) {
        if (n > this.size()) {
            n = this.size();
        }

        ArrayList<E> topN = new ArrayList<>(n);
        Iterator iter = this.iterator();

        for (int i = 0; i < n; i++) {
            topN.add((E) iter.next());
        }

        return topN;
    }
}
