package org.lockiely.test.proxy;

public class RealSubject implements Subject{

    @Override
    public void doSomething() {
        System.out.println("call dosomething()");
    }
}
