package com.wll.test.java.collection;

import java.util.*;

/**
 * Created by wll on 17-7-10.
 */
public class TestMap extends TestBase {

    public static void main(String[] args) {
        TestMap testMap = new TestMap();
        Map map1 = new HashMap<String, String>();
        Map map2 = new LinkedHashMap();
        Map map3 = new TreeMap();
        Map map4 = Collections.synchronizedMap(map1);

        String[] s = new String[]{"1", "a", "dd", "22", "33", "1", "a"};

        for(int i=0; i<s.length; i++) {
            map1.put(s[i], i);
            map2.put(s[i], i);
            map3.put(s[i], i);
        }

        testMap.walk(map1);
        System.out.println("***********");
        testMap.walk(map2);
        System.out.println("***********");
        testMap.walk(map3);
        System.out.println("***********");

        testMap.walk(map4);
        System.out.println("***********");
    }
}
