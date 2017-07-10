package com.wll.test.java.collection;

import java.util.*;

/**
 * Created by wll on 17-7-10.
 */
public class TestSet {

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

        testSet.walkSet(set1);
        System.out.println("***********");
        testSet.walkSet(set2);
        System.out.println("***********");
        testSet.walkSet(set3);
        System.out.println("***********");

        testSet.walkSet(set4);
        System.out.println("***********");
    }

    private void walkSet(Set set){
        Iterator iterator = set.iterator();
        for(;iterator.hasNext();){
            System.out.println(iterator.next());
        }
    }
}
