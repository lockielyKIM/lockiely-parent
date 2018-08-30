package org.lockiely.configuration;

import java.util.Map;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = LockielyProperties.LOCKIELY_PREFIX)
public class LockielyProperties {

    public static final String LOCKIELY_PREFIX = "lockiely";

    // -------- shiro 相关  ------

    /**
     * 全局session 失效时间 （单位：毫秒，默认30分钟）
     */
    private Long globalSessionTimeout = DEFAULT_EXPIRE_TIME;

    /**
     * session 验证失效时间（单位：毫秒， 默认15分钟）
     */
    private Long sessionValidationInterval = 15L * 60 * 1000;

    private String cacheType;

    private static final String DEFAULT_SHIRO_SESSION_PREFIX = "lockiely-shiro-session:";

    private static Long DEFAULT_EXPIRE_TIME = 30L * 60 * 1000;

    private static final String DEFAULT_SHIRO_CACHE_PREFIX = "lockiely-shiro-cache:";

    private static final String PASSWORD_RETRY_CACHE = "lockiely-password-retry-cache:";

    private int retryNum;

    // -------- Redis 相关 ----------

    /**
     *  reids session 过期时间（单位：毫秒，默认30分钟）
     */
    private Long redisSessionExpireTime = DEFAULT_EXPIRE_TIME;

    /**
     * redis cache 失效时间 （单位：毫秒，默认30分钟）
     */
    private Long cacheExpireTime = DEFAULT_EXPIRE_TIME;

    /**
     * shiro session 保存在redis中的前缀
     */
    private String shiroSessionPrefix = DEFAULT_SHIRO_SESSION_PREFIX;

    /**
     * shiro cache 保存在redis中的前缀
     */
    private String shiroCachePrefix = DEFAULT_SHIRO_CACHE_PREFIX;

    // -------- activemq 相关 ----------

    private Map<String, Object> queues;

    private Map<String, Object> topics;

    public Map<String, Object> getQueues() {
        return queues;
    }

    public void setQueues(Map<String, Object> queues) {
        this.queues = queues;
    }

    public Map<String, Object> getTopics() {
        return topics;
    }

    public void setTopics(Map<String, Object> topics) {
        this.topics = topics;
    }

    public String getShiroCachePrefix() {
        return shiroCachePrefix;
    }

    public void setShiroCachePrefix(String shiroCachePrefix) {
        this.shiroCachePrefix = shiroCachePrefix;
    }

    public Long getRedisSessionExpireTime() {
        return redisSessionExpireTime;
    }

    public void setRedisSessionExpireTime(Long redisSessionExpireTime) {
        this.redisSessionExpireTime = redisSessionExpireTime;
    }

    public String getShiroSessionPrefix() {
        return shiroSessionPrefix;
    }

    public void setShiroSessionPrefix(String shiroSessionPrefix) {
        this.shiroSessionPrefix = shiroSessionPrefix;
    }

    public String getCacheType() {
        return cacheType;
    }

    public void setCacheType(String cacheType) {
        this.cacheType = cacheType;
    }

    public Long getGlobalSessionTimeout() {
        return globalSessionTimeout;
    }

    public void setGlobalSessionTimeout(Long globalSessionTimeout) {
        this.globalSessionTimeout = globalSessionTimeout;
    }

    public Long getSessionValidationInterval() {
        return sessionValidationInterval;
    }

    public void setSessionValidationInterval(Long sessionValidationInterval) {
        this.sessionValidationInterval = sessionValidationInterval;
    }

    public Long getCacheExpireTime() {
        return cacheExpireTime;
    }

    public void setCacheExpireTime(Long cacheExpireTime) {
        this.cacheExpireTime = cacheExpireTime;
    }

    public int getRetryNum() {
        return retryNum;
    }

    public void setRetryNum(int retryNum) {
        this.retryNum = retryNum;
    }

    enum CacheType{

        REDIS("redis", "redis"), EHCACHE("ehCache", "ehCache");

        private String type;

        private String des;

        CacheType(String type, String des) {
            this.type = type;
            this.des = des;
        }

        public String getType() {
            return type;
        }

        public String getDes() {
            return des;
        }
    }
}
