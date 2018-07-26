
package org.lockiely.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.io.support.SpringFactoriesLoader;

public class CollectionsTest {

    private String w;

    public static void main(String[] args) {
//        List<String> list = new ArrayList<>();
//        list = SpringFactoriesLoader.loadFactoryNames(EnableAutoConfiguration.class, CollectionsTest.class.getClassLoader());
//        Collections.sort(list);
        List<CollectionsTest> list = new ArrayList<>();
        CollectionsTest d = new CollectionsTest("ss");
        list.add(d);
        CollectionsTest w = new CollectionsTest("ww");
        list.add(w);
        CollectionsTest[] s = list.toArray(new CollectionsTest[list.size()]);
        CollectionsTest h = new CollectionsTest("hh");
        s[1].w = "20";
        System.out.println(list);
        System.out.println(s[0].toString() + "===" + s[1].toString());
    }




    public CollectionsTest(String w) {
        this.w = w;
    }

    @Override
    public String toString() {
        return w.toString();
    }
}
