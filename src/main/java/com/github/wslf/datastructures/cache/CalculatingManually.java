package com.github.wslf.datastructures.cache;

/**
 *
 * @author Wsl_F
 */
class CalculatingManually<CachedT, KeyT> implements Runnable {

    final Cacheable<CachedT, KeyT> object;
    final KeyT key;

    public CalculatingManually(Cacheable<CachedT, KeyT> object, KeyT key) {
        this.object = object;
        this.key = key;
    }

    @Override
    public void run() {
        object.updateValueInCache(key);
    }

}
