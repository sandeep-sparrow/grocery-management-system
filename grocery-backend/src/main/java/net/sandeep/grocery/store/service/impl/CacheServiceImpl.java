package net.sandeep.grocery.store.service.impl;

import lombok.AllArgsConstructor;
import net.sandeep.grocery.store.service.CacheService;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CacheServiceImpl implements CacheService {

    private final CacheManager cacheManager;

    @SuppressWarnings("null")
    @Override
    public Cache getCache(String cacheName){
        return cacheManager.getCache(cacheName);
    }

    @Override
    @CacheEvict(value = {"groceryItems"}, allEntries = true)
    public String removeGroceryItemCache(String cacheName) {
        return "Grocery Cache Evicted!";
    }

    @Override
    @CacheEvict(value = {"categories"}, allEntries = true)
    public String removeCategoryCache(String cacheName) {
        return "Category Cache Evicted!";
    }

    @Override
    @CacheEvict(value = {"groceryItems", "categories"}, allEntries = true)
    public String removeAllCache() {
        return "All Cache Evicted!";
    }
}
