package com.wll.test.java.collection;

import java.util.*;

/**
 * Created by wll on 17-7-10.
 */
public class TestList {

    public static void main(String[] args) {
        TestList testList = new TestList();
        List list1 = new ArrayList<String>();
        List list2 = new LinkedList<String>();
        List list3 = Collections.synchronizedList(list1);

        String[] s = new String[]{"1", "a", "dd", "22", "33", "1", "a", null};

        for(int i=0; i<s.length; i++) {
            list1.add(s[i]);
            list2.add(s[i]);
        }

        testList.walkSet(list1);
        System.out.println("***********");
        testList.walkSet(list2);
        System.out.println("***********");
        testList.walkSet(list3);
        System.out.println("***********");
    }

    private void walkSet(List list){
        Iterator iterator = list.iterator();
        for(;iterator.hasNext();){
            System.out.println(iterator.next());
        }
    }
}
