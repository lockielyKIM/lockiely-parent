package org.lockiely.shiro;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisShiroCache<K, V> implements Cache<K, V> {

    private String shiroCachePrefix;

    private RedisTemplate<K, V> redisTemplate;

    private Long cacheExpireTime;

    private String name;

    public RedisShiroCache(String name, RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
        this.name = name==null?"":name;
    }

    public Long getCacheExpireTime() {
        return cacheExpireTime;
    }

    public void setCacheExpireTime(Long cacheExpireTime) {
        this.cacheExpireTime = cacheExpireTime;
    }

    public String getShiroCachePrefix() {
        return shiroCachePrefix;
    }

    public void setShiroCachePrefix(String shiroCachePrefix) {
        this.shiroCachePrefix = shiroCachePrefix;
    }

    public RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public String getName() {
        return name;
    }

    private K getFullCacheKey(Object key) {
        return (K) (getShiroCachePrefix() + getName() + ":" + key);
    }

    @Override
    public V get(K key) throws CacheException {
        redisTemplate.boundValueOps(getFullCacheKey(key));
        return redisTemplate.boundValueOps(getFullCacheKey(key)).get();
    }

    @Override
    public V put(K key, V value) throws CacheException {
        V old = get(key);
        redisTemplate.boundValueOps(getFullCacheKey(key)).set(value, getCacheExpireTime(), TimeUnit.MINUTES);
        return old;
    }

    @Override
    public V remove(K key) throws CacheException {
        V old = get(key);
        redisTemplate.delete(getFullCacheKey(key));
        return null;
    }

    @Override
    public void clear() throws CacheException {
        redisTemplate.delete(keys());
    }

    @Override
    public int size() {
        return keys().size();
    }

    @Override
    public Set<K> keys() {
        return redisTemplate.keys(getFullCacheKey("*"));
    }

    @Override
    public Collection<V> values() {
        Set<K> set = keys();
        List<V> list = new ArrayList<>();
        for(K k : set){
            list.add(get(k));
        }
        return list;
    }
}
