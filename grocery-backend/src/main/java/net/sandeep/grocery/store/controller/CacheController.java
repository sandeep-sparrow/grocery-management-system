package net.sandeep.grocery.store.controller;

import lombok.AllArgsConstructor;
import net.sandeep.grocery.store.service.CacheService;
import org.springframework.cache.Cache;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cache")
@AllArgsConstructor
public class CacheController {

    private final CacheService cacheService;

    @GetMapping()
    public ResponseEntity<Cache> getCacheItem(@RequestParam("name") String name){
        Cache cache = cacheService.getCache(name);
        return new ResponseEntity<>(cache, HttpStatus.OK);
    }

    @DeleteMapping("/remove-all")
    public  ResponseEntity<String> removeAllCache(@RequestParam("name") String name){
        String message = cacheService.removeCache(name);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
