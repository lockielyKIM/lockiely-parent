package org.lockiely.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class RequestResponseHolder {

    public static HttpServletRequest getHttpServletRequest(){
        if(getServletRequestAttributes()!=null){
            return getServletRequestAttributes().getRequest();
        }
        return null;
    }

    public static HttpServletResponse getHttpServletResponse(){
        if(getServletRequestAttributes()!=null){
            return getServletRequestAttributes().getResponse();
        }
        return null;
    }

    public static ServletRequestAttributes getServletRequestAttributes(){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if(requestAttributes instanceof ServletRequestAttributes) {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
            return servletRequestAttributes;
        }
        return null;
    }
}
