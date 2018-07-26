package org.lockiely.test;

public class ObjectTest {


    private String name;

    public ObjectTest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public static void main(String[] args) {
        ObjectTest obj1 = new ObjectTest("ss");
        ObjectTest obj2 = obj1;
        ObjectTest obj3 = new ObjectTest("hh");
        obj2 = obj3;
//        obj2 = new ObjectTest("ww");
        System.out.println(obj1.toString());
        System.out.println(obj2.toString());

    }
}
