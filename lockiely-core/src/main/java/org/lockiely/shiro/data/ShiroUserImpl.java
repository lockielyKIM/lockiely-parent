package org.lockiely.shiro.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.util.ByteSource;
import org.lockiely.datasource.DynamicDataSource;
import org.lockiely.datasource.support.DynamicDataSourceProperties;
import org.lockiely.persistence.entity.User;
import org.lockiely.persistence.entity.enums.ActiveEnum;
import org.lockiely.persistence.mapper.UserMapper;
import org.lockiely.shiro.ShiroUser;
import org.lockiely.shiro.ShiroUtils;
import org.lockiely.utils.ConvertUtils;
import org.lockiely.utils.SpringContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: lockiely
 * @Date: 2018/7/21 13:51
 * @email: lockiely@163.com
 */
@Service()
@DependsOn("springContextHolder")
@Transactional(readOnly = true, rollbackFor = Exception.class)
@DynamicDataSource(dataSource = DynamicDataSourceProperties.DYNAMIC_DATA_SOURCE_SLAVE)
public class ShiroUserImpl implements IShiroUser{

    @Autowired
    private UserMapper userMapper;

    public static IShiroUser shiroUser(){
        return SpringContextHolder.beanFactoryHolder().getBean(IShiroUser.class);
    }

    @Override
    @DynamicDataSource(dataSource = DynamicDataSourceProperties.DYNAMIC_DATA_SOURCE_SLAVE)
    public User user(String account) {
        User user = userMapper.getByAccount(account);
        if(null == user){
            throw new CredentialsException("账号不存在");
        }
        if(user.getActive() != ActiveEnum.ENABLE){
            throw new LockedAccountException("账号被冻结");
        }
        return user;
    }

    @Override
    public ShiroUser shiroUser(User user) {
        ShiroUser shiroUser = new ShiroUser();
        shiroUser.setAccount(user.getAccount());
        shiroUser.setUserName(user.getUserName());
        shiroUser.setDeptId(user.getDeptId());

        Integer[] roleArray = ConvertUtils.toIntArray(user.getRoleId());
        shiroUser.setRoleList(Arrays.asList(roleArray));
        return shiroUser;
    }

    @Override
    public List<String> findPermissionsByRoleId(Integer roleId) {
        return null;
    }

    @Override
    public String findRoleNameByRoleId(Integer roleId) {
        return null;
    }

    @Override
    public SimpleAuthenticationInfo info(ShiroUser shiroUser, User user, String realmName) {
        String credentials = user.getPassword();
        return new SimpleAuthenticationInfo(shiroUser, credentials, ShiroUtils.getCredentialsSalt(user), realmName);
    }
}
