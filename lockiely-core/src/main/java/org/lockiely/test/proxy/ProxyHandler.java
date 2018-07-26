package org.lockiely.test.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyHandler implements InvocationHandler {

    private Object proxid;

    public ProxyHandler(Object proxid) {
        this.proxid = proxid;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        return method.invoke(proxid, args);
    }
}
