package org.lockiely.test.proxy;

import java.lang.reflect.Proxy;

public class DynamicProxy {

    public static void main(String[] args) {
        RealSubject real = new RealSubject();
        ProxyHandler handler = new ProxyHandler(real);
        Subject proxySubject = (Subject) Proxy.newProxyInstance(Subject.class.getClassLoader(), new Class[]{Subject.class}, handler);
        proxySubject.doSomething();
    }
}
