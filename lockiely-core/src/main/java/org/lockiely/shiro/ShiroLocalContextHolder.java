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

    private final static ThreadLocal<Map<String, Integer>> ACCOUNT_RETRY_NUM = new NamedThreadLocal<>("account login retry num");

    public static Integer getAccountLoginRetryNum(String account){
        return ACCOUNT_RETRY_NUM.get().get(account);
    }

    public static void setAccountRetryNum(String account, Integer retryNum){
        if(ACCOUNT_RETRY_NUM.get() != null && ACCOUNT_RETRY_NUM.get().containsKey(account)){
            ACCOUNT_RETRY_NUM.get().put(account, retryNum);
        }else{
            Map<String, Integer> retryMap = new HashMap<>(1);
            retryMap.put(account, retryNum);
            ACCOUNT_RETRY_NUM.set(retryMap);
        }
    }
}
