package net.sandeep.grocery.store.service;

import org.springframework.cache.Cache;

public interface CacheService {

    Cache getCache(String cacheName);
    String removeCache(String cacheName);

}
