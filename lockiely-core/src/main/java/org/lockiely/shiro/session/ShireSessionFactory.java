package org.lockiely.shiro.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionFactory;
import org.apache.shiro.session.mgt.SimpleSession;

/**
 * @author: lockiely
 * @Date: 2018/7/27 12:23
 * @email: lockiely@163.com
 */
public class ShireSessionFactory implements SessionFactory {

    @Override
    public Session createSession(SessionContext initData) {
        if (initData != null) {
            String host = initData.getHost();
            if (host != null) {
                return new RedisSession(host);
            }
        }
        return new RedisSession();
    }
}
