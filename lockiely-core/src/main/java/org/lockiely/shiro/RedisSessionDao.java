package org.lockiely.shiro;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisSessionDao extends EnterpriseCacheSessionDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisSessionDao.class);

    private Long expireTime;

    private String shiroSessionPrefix;

    private RedisTemplate redisTemplate;

    public RedisSessionDao(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

    public String getShiroSessionPrefix() {
        return shiroSessionPrefix;
    }

    private String getFullSessionId(Serializable sessionId){
        return this.shiroSessionPrefix + sessionId.toString();
    }

    public void setShiroSessionPrefix(String shiroSessionPrefix) {
        this.shiroSessionPrefix = shiroSessionPrefix;
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = super.doCreate(session);
        LOGGER.debug("创建session:{}", session.getId());
        redisTemplate.opsForValue().set(getFullSessionId(sessionId), session);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        LOGGER.debug("获取session:{}", sessionId);
        Session session = super.doReadSession(sessionId);
        if(session == null) {
            session = (Session) redisTemplate.opsForValue().get(getFullSessionId(sessionId));
        }
        return session;
    }

    @Override
    protected void doUpdate(Session session) {
        super.doUpdate(session);
        LOGGER.debug("获取session:{}", session.getId());
        String key = getFullSessionId(session.getId());
        if(!redisTemplate.hasKey(key)){
            redisTemplate.opsForValue().set(key, session, getExpireTime(), TimeUnit.MINUTES);
        }
    }

    @Override
    protected void doDelete(Session session) {
        LOGGER.debug("删除session:{}", session.getId());
        super.doDelete(session);
        redisTemplate.delete(getFullSessionId(session.getId()));
    }
}
