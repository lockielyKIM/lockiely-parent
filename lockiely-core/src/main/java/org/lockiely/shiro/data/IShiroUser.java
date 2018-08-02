package org.lockiely.shiro.data;

import java.util.List;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.lockiely.datasource.DynamicDataSource;
import org.lockiely.datasource.support.DynamicDataSourceProperties;
import org.lockiely.persistence.entity.User;
import org.lockiely.shiro.ShiroUser;

/**
 * 定义shirorealm所需数据的接口
 *
 * @author: lockiely
 * @Date: 2018/7/21 13:50
 * @email: lockiely@163.com
 */
public interface IShiroUser {

    /**
     * 根据账号获取登录用户
     *
     * @param account 账号
     */
    User user(String account);

    /**
     * 根据系统用户获取Shiro的用户
     *
     * @param user 系统用户
     */
    ShiroUser shiroUser(User user);

    /**
     * 获取权限列表通过角色id
     *
     * @param roleId 角色id
     */
    List<String> findPermissionsByRoleId(Integer roleId);

    /**
     * 根据角色id获取角色名称
     *
     * @param roleId 角色id
     */
    String findRoleNameByRoleId(Integer roleId);

    /**
     * 获取shiro的认证信息
     */
    SimpleAuthenticationInfo info(ShiroUser shiroUser, User user, String realmName);
}
