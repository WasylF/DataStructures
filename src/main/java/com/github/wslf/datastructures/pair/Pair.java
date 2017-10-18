package com.github.wslf.datastructures.pair;

/**
 * Class to represent object that consists of two items.
 *
 * @author Wsl_F
 * @param <FirstT> type of the first item
 * @param <SecondT> type of the second item
 */
public class Pair<FirstT, SecondT> {

    /**
     * First item
     */
    private FirstT first;
    /**
     * second item
     */
    private SecondT second;

    /**
     * Default constructor. Initialize both items by null value.
     */
    public Pair() {
        this(null, null);
    }

    /**
     * Constructor.
     *
     * @param first value of the first item
     * @param second value of the second item
     */
    public Pair(FirstT first, SecondT second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Returns first item of the pair.
     * @return first item of the pair
     */
    public FirstT getFirst() {
        return first;
    }

    /**
     * Set value of first item of the pair.
     * @param first new value of the first item.
     */
    public void setFirst(FirstT first) {
        this.first = first;
    }

    /**
     * Returns second item of the pair.
     * @return second item of the pair
     */
    public SecondT getSecond() {
        return second;
    }

    /**
     * Set value of second item of the pair.
     * @param second new value of the second item.
     */
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
