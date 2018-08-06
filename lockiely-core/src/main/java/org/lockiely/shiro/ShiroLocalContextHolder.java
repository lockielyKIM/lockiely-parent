package org.lockiely.shiro;

import java.util.HashMap;
import java.util.Map;
import org.springframework.core.NamedThreadLocal;

/**
 * @author: lockiely
 * @Date: 2018/7/26 15:09
 * @email: lockiely@163.com
 */
public class ShiroLocalContextHolder {

    private final static ThreadLocal<Integer> ACCOUNT_RETRY_NUM = new NamedThreadLocal<>("account login retry num");

    public static Integer getAccountLoginRetryNum(){
        return ACCOUNT_RETRY_NUM.get();
    }

    public static void setAccountRetryNum(Integer retryNum){
        ACCOUNT_RETRY_NUM.set(retryNum);
    }
}
