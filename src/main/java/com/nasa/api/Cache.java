package com.nasa.api;

import java.time.Instant;

public class Cache {

    private static int DEFAULT_CACHE_EXPIRATION = 600;

    private Object data;
    private Instant lastCached;
    private int cacheExpiration;  // in seconds

    public Cache(int cacheExpiration) {
        this.data = null;
        this.lastCached = null;
        this.cacheExpiration = cacheExpiration;
    }

    public Cache() {
        this(DEFAULT_CACHE_EXPIRATION);
    }

    public Object getData() {
        return data;
    }

    public void cacheData(Object data) {
        this.data = data;
        this.lastCached = Instant.now();
    }

    public Instant getLastCached() {
        return lastCached;
    }

    public int getCacheExpiration() {
        return cacheExpiration;
    }

    public void setCacheExpiration(int cacheExpiration) {
        this.cacheExpiration = cacheExpiration;
    }

    public boolean isCacheExpired() {
        return lastCached == null || data == null? 
            true : lastCached.plusSeconds(cacheExpiration).compareTo(Instant.now()) <= 0;
    }

    public void forceExpire() {
        this.data = null;
        this.lastCached = null;
    }

}