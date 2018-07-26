package org.lockiely.shiro;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.lockiely.configuration.LockielyProperties;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisShiroCacheManager implements CacheManager {

    private RedisTemplate redisTemplate;

    private LockielyProperties lockielyProperties;

    public RedisShiroCacheManager(RedisTemplate redisTemplate, LockielyProperties lockielyProperties){
        this.redisTemplate = redisTemplate;
        this.lockielyProperties = lockielyProperties;
    }

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        RedisShiroCache redisShiroCache = new RedisShiroCache(name, redisTemplate);
        redisShiroCache.setShiroCachePrefix(lockielyProperties.getShiroCachePrefix());
        redisShiroCache.setCacheExpireTime(lockielyProperties.getCacheExpireTime());
        return redisShiroCache;
    }

    public RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
