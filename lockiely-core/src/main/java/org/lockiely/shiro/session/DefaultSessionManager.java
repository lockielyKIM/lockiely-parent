package org.lockiely.shiro.session;

import java.io.Serializable;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.shiro.session.mgt.SessionFactory;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.lockiely.utils.StringUtils;

public class DefaultSessionManager extends DefaultWebSessionManager {

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
    public void setSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }
}
