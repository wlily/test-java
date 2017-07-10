package com.wll.test.java.collection;

import java.util.*;

/**
 * Created by wll on 17-7-10.
 */
public class TestSet extends TestBase{

    public static void main(String[] args) {
        TestSet testSet = new TestSet();
        Set set1 = new HashSet();
        Set set2 = new LinkedHashSet();
        Set set3 = new TreeSet();
        Set set4 = Collections.synchronizedSet(set1);

        String[] s = new String[]{"1", "a", "dd", "22", "33", "1", "a"};

        for(int i=0; i<s.length; i++) {
            set1.add(s[i]);
            set2.add(s[i]);
            set3.add(s[i]);
        }

        testSet.walk(set1);
        System.out.println("***********");
        testSet.walk(set2);
        System.out.println("***********");
        testSet.walk(set3);
        System.out.println("***********");

        testSet.walk(set4);
        System.out.println("***********");
    }
}
