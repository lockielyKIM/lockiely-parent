package org.lockiely.test;

import java.lang.reflect.Method;
import java.util.stream.Stream;

public class ClassTest {

    private String privateName;

    public String publicName;

    public String getPrivateName() {
        return privateName;
    }

    public void setPrivateName(String privateName) {
        this.privateName = privateName;
    }

    public String getPublicName() {
        return publicName;
    }

    public void setPublicName(String publicName) {
        this.publicName = publicName;
    }

    private void setName(){

    }

    public static void main(String[] args) {
        ClassTest classTest = new ClassTest();
        Method[] methods = classTest.getClass().getDeclaredMethods();
        Method[] methods1 = classTest.getClass().getMethods();
        Stream.of(methods).forEach(f -> System.out.println(f.getName()));
        System.out.println("=============");
        Stream.of(methods1).forEach(f -> System.out.println(f.getName()));

    }
}
