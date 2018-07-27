package org.lockiely.shiro.session;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.session.mgt.SimpleSession;

/**
 * @author: lockiely
 * @Date: 2018/7/27 12:31
 * @email: lockiely@163.com
 */
public class RedisSession extends SimpleSession {

    private Serializable id;
    private Date startTimestamp;
    private Date stopTimestamp;
    private Date lastAccessTime;
    private long timeout;
    private boolean expired;
    private String host;
    private Map<Object, Object> attributes;

    public RedisSession() {
        this.timeout = DefaultSessionManager.DEFAULT_GLOBAL_SESSION_TIMEOUT;
        this.startTimestamp = new Date();
        this.lastAccessTime = this.startTimestamp;
    }

    public RedisSession(String host) {
        this();
        this.host = host;
    }

    @Override
    public Serializable getId() {
        return id;
    }

    @Override
    public void setId(Serializable id) {
        this.id = id;
    }

    @Override
    public Date getStartTimestamp() {
        return startTimestamp;
    }

    @Override
    public void setStartTimestamp(Date startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    @Override
    public Date getStopTimestamp() {
        return stopTimestamp;
    }

    @Override
    public void setStopTimestamp(Date stopTimestamp) {
        this.stopTimestamp = stopTimestamp;
    }

    @Override
    public Date getLastAccessTime() {
        return lastAccessTime;
    }

    @Override
    public void setLastAccessTime(Date lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }

    @Override
    public long getTimeout() {
        return timeout;
    }

    @Override
    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    @Override
    public boolean isExpired() {
        return expired;
    }

    @Override
    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public void setHost(String host) {
        this.host = host;
    }

    @Override
    public Map<Object, Object> getAttributes() {
        return attributes;
    }

    @Override
    public void setAttributes(Map<Object, Object> attributes) {
        this.attributes = attributes;
    }
}
