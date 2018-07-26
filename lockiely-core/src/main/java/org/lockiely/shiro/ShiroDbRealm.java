package org.lockiely.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.lockiely.persistence.entity.User;
import org.lockiely.shiro.data.IShiroUser;
import org.lockiely.shiro.data.ShiroUserImpl;

public class ShiroDbRealm extends AuthorizingRealm {

    /**
     * 权限认证
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        return info;
    }

    /**
     * 身份认证， 登录
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
        throws AuthenticationException {
        IShiroUser  shiroUserService = ShiroUserImpl.shiroUser();
        User user = shiroUserService.user((String) token.getPrincipal());
        ShiroUser shiroUser = shiroUserService.shiroUser(user);
        SimpleAuthenticationInfo info = shiroUserService.info(shiroUser, user, super.getName());
        return info;
    }
}
