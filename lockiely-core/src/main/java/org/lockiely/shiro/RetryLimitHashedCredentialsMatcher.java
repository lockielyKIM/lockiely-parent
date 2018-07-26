package org.lockiely.shiro;

import java.util.concurrent.atomic.AtomicInteger;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Value;

/**
 * 用于登录密码错误情况下，可以重试的次数（retryNum），重试次数记录在lockielyProperties中
 * 并且会记录在shiro / ehCache 缓存中，失效时间为shiro / ehCache 中设置的时间（在lockielyProperties中记录）
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {

    private Cache<String, AtomicInteger> passwordRetryCache;

//    @Value("#{lockielyProperties['retryNum']}")
    private int retryNum;

    private static final Integer DEFAULT_RETRY_NUM = 5;

    public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager){
        this.passwordRetryCache = cacheManager.getCache("passwordRetryCache");
        if(this.retryNum <= 0){
            retryNum = DEFAULT_RETRY_NUM;
        }
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {

        String userName = (String) token.getPrincipal();

        AtomicInteger retryCache = passwordRetryCache.get(userName);
        if(retryCache == null){
            retryCache = new AtomicInteger(0);
        }
        if(retryCache.incrementAndGet() > retryNum){
            throw new ExcessiveAttemptsException("重试次数过多");
        }
        passwordRetryCache.put(userName, retryCache);

        boolean matches = super.doCredentialsMatch(token, info);
        if(matches){
            passwordRetryCache.remove(userName);
        }else{
            ShiroLocalContextHolder.setAccountRetryNum(userName, retryCache.get());
        }
        return matches;
    }

    public int getRetryNum() {
        return retryNum;
    }

    public void setRetryNum(int retryNum) {
        this.retryNum = retryNum;
    }
}
