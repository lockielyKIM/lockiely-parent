package org.lockiely.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.lockiely.persistence.entity.User;

/**
 * @author: lockiely
 * @Date: 2018/7/21 14:49
 * @email: lockiely@163.com
 */
public class ShiroUtils {

    /**
     * 加盐参数
     */
    public static final String hashAlgorithmName = "MD5";

    /**
     * 循环次数
     */
    public final static int hashIterations = 3;

    /**
     * 获取随机密码盐
     * @return
     */
    public static String getSalt(){
        return new SecureRandomNumberGenerator().nextBytes().toHex();
    }

    /**
     * 证书盐 = 用户名 + 密码盐
     * @param user
     * @return
     */
    public static ByteSource getCredentialsSalt(User user){
        if(user.getSalt()==null){
            user.setSalt(getSalt());
        }
        String credentialsSalt = user.getAccount() + user.getSalt();
        return new Md5Hash(credentialsSalt);
    }

    /**
     * 获得加密密码：通过
     * @return
     */
    public static String getCredentials(User user, String password){
        String credentials = password;
        ByteSource slat = getCredentialsSalt(user);
        return new SimpleHash(hashAlgorithmName, credentials, slat, hashIterations).toHex();
    }


    /**
     * 获取封装的 ShiroUser
     *
     * @return ShiroUser
     */
    public static ShiroUser getUser() {
        if (isGuest()) {
            return null;
        } else {
            return (ShiroUser) getSubject().getPrincipals().getPrimaryPrincipal();
        }
    }

    /**
     * 验证当前用户是否为“访客”，即未认证（包含未记住）的用户。用user搭配使用
     *
     * @return 访客：true，否则false
     */
    public static boolean isGuest() {
        return !isUser();
    }

    /**
     * 认证通过或已记住的用户。与guset搭配使用。
     *
     * @return 用户：true，否则 false
     */
    public static boolean isUser() {
        return getSubject() != null && getSubject().getPrincipal() != null;
    }

    /**
     * 获取当前 Subject
     *
     * @return Subject
     */
    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    public static boolean isAuthenticated(){
        return getSubject() != null && getSubject().isAuthenticated();
    }



}
