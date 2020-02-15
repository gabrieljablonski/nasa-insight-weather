package com.nasa.api;

import java.time.Instant;

public class Cache {

    private static int DEFAULT_EXPIRATION = 600;

    private Object data;
    private Instant lastCached;
    private int expiration;  // in seconds

    public Cache(int expiration) {
        this.data = null;
        this.lastCached = null;
        this.expiration = expiration;
    }

    public Cache() {
        this(DEFAULT_EXPIRATION);
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

    public int getExpiration() {
        return expiration;
    }

    public void setExpiration(int expiration) {
        this.expiration = expiration;
    }

    public boolean isExpired() {
        return lastCached == null || data == null? 
            true : lastCached.plusSeconds(expiration).compareTo(Instant.now()) <= 0;
    }

    public void forceExpire() {
        this.data = null;
        this.lastCached = null;
    }

}