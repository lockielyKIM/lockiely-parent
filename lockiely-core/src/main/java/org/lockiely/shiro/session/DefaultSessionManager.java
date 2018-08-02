package org.lockiely.shiro.session;

import java.io.Serializable;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SessionFactory;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionKey;
import org.apache.shiro.web.util.WebUtils;
import org.lockiely.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultSessionManager extends DefaultWebSessionManager {

    private static final Logger log = LoggerFactory.getLogger(DefaultWebSessionManager.class);

    /**
     * 在请求头中使用此字段，把Cookie的值放在此处就可以
     * 登录后默认是生成Cookie，key为lockiely-shire-cookie
     */
    private static final String AUTHENRIZATION = "Authenrization";

    private static final String REFERENCED_SESSION_ID_SOURCE = "Stateless request";

    public DefaultSessionManager() {
        super();
    }

    /**
     * 此处可以进行sesseionId 的处理， eg: 加密
     */
    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        String id = WebUtils.toHttp(request).getHeader(AUTHENRIZATION);
        //如果请求头中有 Authorization 则其值为sessionId
        if(!StringUtils.isEmpty(id)){
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, REFERENCED_SESSION_ID_SOURCE);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, id);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            return id;
        }else{
            //否则按默认规则从cookie取sessionId
            return super.getSessionId(request, response);
        }
    }

    @Override
    protected Session retrieveSession(SessionKey sessionKey) throws UnknownSessionException {
        // 获取sessionId
        Serializable sessionId = getSessionId(sessionKey);

        ServletRequest request = null;
        // 在 Web 下使用 shiro 时这个 sessionKey 是 WebSessionKey 类型的
        // 若是在web下使用，则获取request
        if (sessionKey instanceof WebSessionKey) {
            request = ((WebSessionKey) sessionKey).getServletRequest();
        }

        // 尝试从request中获取session
        if (request != null && sessionId != null) {
            Object sessionObj = request.getAttribute(sessionId.toString());
            if (sessionObj != null) {
                log.info("从request获取到session:{}", sessionId);
                return (Session) sessionObj;
            }
        }

        // 若从request中获取session失败,则从redis中获取session,并把获取到的session存储到request中方便下次获取
        Session session = super.retrieveSession(sessionKey);
        if (request != null && sessionId != null) {
            log.info("存储session到request中:{}", sessionId);
            request.setAttribute(sessionId.toString(), session);
        }

        return session;
    }

}
