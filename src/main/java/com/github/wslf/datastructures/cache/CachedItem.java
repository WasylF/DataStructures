package com.github.wslf.datastructures.cache;

/**
 *
 * @author Wsl_F
 */
class CachedItem<CachedT, KeyT> implements Comparable<CachedItem<CachedT, KeyT>> {

    private static final int INC = 1;
    private final KeyT key;
    private CachedT value;

    private long createTime;
    private long accessTime;
    private long accessNumber;

    /**
     * Item with bigger accessNumer is more priority. If items have the same
     * accessNumber, item that accessed more recent is more priority.
     *
     * @param o second cached item
     * @return {@code this} more priority than {@code o} => negative value,
     * {@code this} equals priority {@code o} => zero, {@code this} lower
     * priority {@code  o} => positive value
     */
    @Override
    public int compareTo(CachedItem<CachedT, KeyT> o) {
        if (this == o) {
            return 0;
        }

        return (this.accessNumber == o.accessNumber)
                ? Long.compare(o.accessTime, this.accessTime)
                : Long.compare(o.accessNumber, this.accessNumber);
    }

    public CachedItem(KeyT key, CachedT value, long createTime, long accessTime, long accessNumber) {
        this.key = key;
        this.value = value;
        this.createTime = createTime;
        this.accessTime = accessTime;
        this.accessNumber = accessNumber;
    }

    public CachedItem(KeyT key, CachedT value, long creationTime) {
        this.key = key;
        this.value = value;
        this.createTime = creationTime;
        this.accessTime = this.createTime;
        this.accessNumber = 1;
    }

    public CachedItem(KeyT key, CachedT value) {
        this.key = key;
        this.value = value;
        this.createTime = System.currentTimeMillis();
        this.accessTime = this.createTime;
        this.accessNumber = 1;
    }

    public void use() {
        this.accessTime = System.currentTimeMillis();
        this.accessNumber += INC + 1;
    }

    public void decrease() {
        accessNumber--;
    }

    public CachedT getValue() {
        return this.value;
    }

    public KeyT getKey() {
        return key;
    }

    public void updateValue(CachedT newValue) {
        this.value = newValue;
        this.createTime = System.currentTimeMillis();
    }

    public long timeSinceCreated(long curTime) {
        return curTime - createTime;
    }

    public long getAccessNumber() {
        return this.accessNumber;
    }

    public long getCreationTime() {
        return createTime;
    }
}
