package org.lockiely.test;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;

/**
 * @author: lockiely
 * @Date: 2018/7/21 14:43
 * @email: lockiely@163.com
 */
public class ConverterTest {

    public static void main(String[] args) {
        String userName = "lijin";

        String salt = new SecureRandomNumberGenerator().nextBytes().toHex();

        
        System.out.println(userName + salt);
    }
}
