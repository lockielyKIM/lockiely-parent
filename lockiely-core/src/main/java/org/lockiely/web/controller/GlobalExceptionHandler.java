package org.lockiely.web.controller;

import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.UnknownSessionException;
import org.lockiely.shiro.ShiroLocalContextHolder;
import org.lockiely.shiro.ShiroUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: lockiely
 * @Date: 2018/7/27 16:08
 * @email: lockiely@163.com
 */

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ShiroException.class)
    @ResponseBody
    public Object shiroAuthentication(ShiroException e) {
        UserLoginHandler userLoginHandler = new UserLoginHandler();
        if (e instanceof IncorrectCredentialsException) {
            Integer loginRetryNum = ShiroLocalContextHolder.getAccountLoginRetryNum(ShiroUtils.getUser().getAccount());
            userLoginHandler.setErrorMsg("密码错误");
            userLoginHandler.setLoginRetryNum(loginRetryNum);
            return userLoginHandler;
        }else if(e instanceof ExcessiveAttemptsException) {
            userLoginHandler.setErrorMsg("密码重试超过5次，已锁定");
            return userLoginHandler;
        }else if(e instanceof LockedAccountException) {
            userLoginHandler.setErrorMsg("账号已被锁定");
            return userLoginHandler;
        }else if(e instanceof DisabledAccountException) {
            userLoginHandler.setErrorMsg("账号已被禁用");
            return userLoginHandler;
        }else if(e instanceof ExpiredCredentialsException) {
            userLoginHandler.setErrorMsg("账号已过期");
            return userLoginHandler;
        }else if(e instanceof UnknownAccountException) {
            userLoginHandler.setErrorMsg("账号不存在");
            return userLoginHandler;
        }else if(e instanceof UnknownSessionException || e instanceof InvalidSessionException) {
            userLoginHandler.setErrorMsg("session超时");
            return userLoginHandler;
        }
        return new UserLoginHandler();
    }



    class UserLoginHandler {

        Integer LoginRetryNum;

        String errorMsg;

        final int MAX_LOGIN_RETRY_NUM = 5;

        UserLoginHandler(){

        }

        public Integer getLoginRetryNum() {
            return LoginRetryNum;
        }

        public void setLoginRetryNum(Integer loginRetryNum) {
            LoginRetryNum = loginRetryNum;
        }

        public String getErrorMsg() {
            return errorMsg;
        }

        public void setErrorMsg(String errorMsg) {
            this.errorMsg = errorMsg;
        }
    }
}
