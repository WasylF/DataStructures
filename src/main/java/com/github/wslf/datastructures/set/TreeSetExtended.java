package com.github.wslf.datastructures.set;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Extension of standard java TreeSet collection. It additionally allows get
 * list of first / last @code{K} elements.
 *
 * @author Wsl_F
 * @param <E> type of elements in the set
 */
public class TreeSetExtended<E> extends TreeSet<E> {

    public TreeSetExtended() {
        super();
    }

    public TreeSetExtended(Comparator<? super E> comparator) {
        super(comparator);
    }

    public TreeSetExtended(Collection<? extends E> c) {
        super(c);
    }

    public TreeSetExtended(SortedSet<E> s) {
        super(s);
    }

    /**
     * Returns sorted list in non-decreasing order of first (lower) {@code k}
     * elements.
     * <br><br>
     * Examples:
     *
     * <br>{ 1, 2, 3 }.firstK(2) = { 1, 2 }
     * <br>{ 2, 3, 12 }.firstK(10) = { 2, 3, 12 }
     * <br>{ 5, 9 }.firstK(1) = { 5 }
     *
     *
     * @param k number of requested elements. If k greater than number of
     * elements in the set, then all elements be returned.
     * @return sorted {@code ArrayList<E>} of first (lower) {@code k} elements
     */
    public ArrayList<E> getFirstK(int k) {
        k = ensureBoundary(k, 0, this.size());

        ArrayList<E> firstK = new ArrayList<>(k);
        Iterator iter = this.iterator();

        for (int i = 0; i < k; i++) {
            firstK.add((E) iter.next());
        }

        return firstK;
    }

    /**
     * Returns sorted list in non-ascending order of last (greatest) {@code k}
     * elements.
     * <br><br>
     * Examples:
     *
     * <br>{ 1, 2, 3 }.lastK(2) = { 2, 3 }
     * <br>{ 2, 3, 12 }.lastK(10) = { 2, 3, 12 }
     * <br>{ 5, 9 }.lastK(1) = { 9 }
     *
     *
     * @param k number of requested elements. If k greater than number of
     * elements in the set, then all elements be returned.
     * @return sorted {@code ArrayList<E>} of first (lower) {@code k} elements
     */
    public ArrayList<E> getLastK(int k) {
        k = ensureBoundary(k, 0, this.size());

        ArrayList<E> lastK = new ArrayList<>(k);
        Iterator iter = this.descendingIterator();

        for (int i = 0; i < k; i++) {
            lastK.add((E) iter.next());
        }

        return lastK;
    }

    /**
     * Cast value {@code val} to boundaries [min; max] inclusive.
     *
     * @param val value
     * @param min minimum value
     * @param max maximum value
     * @return casted value of {@code  val} to boundaries [min; max] inclusive
     */
    private static int ensureBoundary(int val, int min, int max) {
        if (val <= min) {
            return min;
        }
        if (val >= max) {
            return max;
        }
        return val;
    }
}
