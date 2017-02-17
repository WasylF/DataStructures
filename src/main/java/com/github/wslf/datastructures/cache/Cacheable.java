package com.github.wslf.datastructures.cache;

import java.util.HashMap;
import java.util.Map;
import com.github.wslf.datastructures.set.TreeSetWslF;
import java.util.HashSet;
import java.util.Set;

/**
 * Stores data at the cache and updates some of them.
 *
 * @author Wsl_F
 * @param <CachedT> type of cached data
 * @param <KeyT> type of key
 */
public abstract class Cacheable<CachedT, KeyT> {

    /**
     * Maximum number of cached items.
     */
    private final int MAX_CACHED_DATA;

    /**
     * Maximum number of most used cached items for which value is updated in
     * background.
     */
    private final int MAX_UPDATED_DATA;

    /**
     * Time (in milliseconds) after that value of top {@code MAX_UPDATED_DATE}
     * used items is updated.
     */
    private final long TIME_TO_UPDATE_MS;

    /**
     * Constructor.
     *
     * @param MAX_CACHED_DATA Maximum number of cached items.
     * @param MAX_UPDATED_DATA Maximum number of most used cached items for
     * which value is updated in background.
     * @param TIME_TO_UPDATE_MS Time after that value of top
     * {@code MAX_UPDATED_DATE} used items is updated.
     */
    public Cacheable(int MAX_CACHED_DATA, int MAX_UPDATED_DATA, long TIME_TO_UPDATE_MS) {
        this.MAX_CACHED_DATA = MAX_CACHED_DATA;
        this.MAX_UPDATED_DATA = MAX_UPDATED_DATA;
        this.TIME_TO_UPDATE_MS = TIME_TO_UPDATE_MS;
    }

    private final TreeSetWslF<CachedItem<CachedT, KeyT>> cacheSet = new TreeSetWslF<>();

    private final Map<KeyT, CachedItem<CachedT, KeyT>> cacheMap = new HashMap<>();

    /**
     * getting value by long time calculation or so on
     *
     * @param key key
     * @return calculated value
     */
    public abstract CachedT getValueManually(KeyT key);

    private CachedT getValueCached(KeyT key) {
        CachedItem<CachedT, KeyT> cachedItem = cacheMap.get(key);
        cachedItem.use();
        if (cachedItem.timeSinceCreated(System.currentTimeMillis())
                > TIME_TO_UPDATE_MS) {
            update(cachedItem);
        }

        return cachedItem.getValue();
    }

    /**
     * Get value by key. If cache contains cached value, it be returned.
     * Otherwise value be calculated manually.
     *
     * @param key specific key
     * @return value
     */
    public CachedT getValue(KeyT key) {
        CachedT result;
        if (contains(key)) {
            result = getValueCached(key);
        } else {
            result = getValueManually(key);
            addToCacheIfNeed(key, result);
        }

        updateCache();
        return result;
    }

    private void decreaseUsing() {
        cacheSet.forEach((item) -> {
            item.decrease();
        });
    }

    /**
     * Check does cache contains specific key
     *
     * @param key key for check
     * @return if cache contains key return true, otherwise - false
     */
    public boolean contains(KeyT key) {
        return cacheMap.containsKey(key);
    }

    private void addToCacheIfNeed(KeyT key, CachedT value) {
        if (!contains(key)) {
            boolean add = false;
            CachedItem<CachedT, KeyT> newItem = new CachedItem<>(key, value);
            add = (cacheMap.size() < MAX_CACHED_DATA)
                    || (cacheSet.size() == MAX_CACHED_DATA
                    && newItem.compareTo(cacheSet.last()) < 0);

            if (add) {
                addToCache(newItem);
                removeExcess();
            }
        }
    }

    /**
     * adding item to cache without any check
     *
     * @param newItem item to be added to the cache
     */
    private void addToCache(CachedItem<CachedT, KeyT> newItem) {
        cacheMap.put(newItem.getKey(), newItem);
        cacheSet.add(newItem);
    }

    private void removeExcess() {
        while (cacheSet.size() > MAX_CACHED_DATA) {
            CachedItem<CachedT, KeyT> excessItem = cacheSet.last();
            remove(excessItem.getKey());
        }
    }

    /**
     * remove item with specified key from the cache
     *
     * @param key
     */
    public void remove(KeyT key) {
        CachedItem<CachedT, KeyT> item = cacheMap.remove(key);
        if (item != null) {
            cacheSet.remove(item);
        }
    }

    void updateValueInCache(KeyT key) {
        CachedT newValue = getValueManually(key);

        CachedItem<CachedT, KeyT> currentItem = cacheMap.get(key);
        CachedItem<CachedT, KeyT> newItem = new CachedItem<>(key, newValue, currentItem.getAccessNumber());

        synchronized (this) {
            remove(key);
            addToCache(newItem);
        }

        updating.remove(key);
    }

    private void updateCache() {
        decreaseUsing();

        long curTime = System.currentTimeMillis();
        for (CachedItem<CachedT, KeyT> item : cacheSet.getFirstN(MAX_UPDATED_DATA)) {
            if (item.timeSinceCreated(curTime) > TIME_TO_UPDATE_MS) {
                update(item);
            }
        }
    }

    /**
     * contains keys of items that updating now in separate thread
     */
    private final Set<KeyT> updating = new HashSet<>();

    /**
     * updating cached value of item in separate thread
     *
     * @param item
     */
    private void update(CachedItem<CachedT, KeyT> item) {
        boolean update = false;
        synchronized (updating) {
            if (!updating.contains(item.getKey())) {
                updating.add(item.getKey());
                update = true;
            }
        }

        if (update) {
            new Thread(new CalculatingManually<>(this, item.getKey())).start();
        }
    }

    /**
     *
     * @return set, that contains all cached keys.
     */
    public Set<KeyT> getCachedKeys() {
        return cacheMap.keySet();
    }

}
