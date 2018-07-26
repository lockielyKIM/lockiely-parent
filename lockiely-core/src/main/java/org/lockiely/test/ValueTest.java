package org.lockiely.test;

public class ValueTest {

    public static void main(String[] args) {
        String name = new String(ValueTest.class.getName() + "#test");
        String canonicalName = new String(name);
        canonicalName = "ss";
        System.out.println(canonicalName + "--" + name);
    }
}
