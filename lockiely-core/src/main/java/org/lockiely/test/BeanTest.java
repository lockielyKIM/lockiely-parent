package org.lockiely.test;

public class BeanTest {

    private Boolean flag;

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public Boolean getFlag() {
        return flag;
    }

    public static void main(String[] args) {
        BeanTest test1 = new BeanTest();
    }
}
