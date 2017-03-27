package com.github.wslf.datastructures.pair;

/**
 *
 * @author Wsl_F
 */
public class Pair<FirstT, SecondT> {

    private FirstT first;
    private SecondT second;

    public Pair() {
        this(null, null);
    }

    public Pair(FirstT first, SecondT second) {
        this.first = first;
        this.second = second;
    }

    public FirstT getFirst() {
        return first;
    }

    public void setFirst(FirstT first) {
        this.first = first;
    }

    public SecondT getSecond() {
        return second;
    }

    public void setSecond(SecondT second) {
        this.second = second;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass().equals(Pair.class)) {
            try {
                Pair<FirstT, SecondT> pair = (Pair<FirstT, SecondT>) obj;
                boolean result = first == pair.first || first.equals(pair.first);
                result &= second == pair.second || second.equals(pair.second);
                return result;
            } catch (Exception ex) {

            }
        }

        return false;
    }

    @Override
    public int hashCode() {
        int f = first == null ? -1 : first.hashCode();
        int s = second == null ? -2 : second.hashCode();
        int hash = 31 + 3 * f;
        hash = 37 * hash + 5 * s;

        return hash;
    }

}
