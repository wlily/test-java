package com.wll.test.java.collection;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by wll on 17-7-13.
 */
public class TestConcurrentHashMap {

    public static void main(String[] args) {
        String[] s = new String[]{"1", "a", "dd", "22", "33", "1", "a"};

        Map map = new ConcurrentHashMap();

        for(int i=0; i<s.length; i++) {
            map.put(s[i], i);
        }
    }

}
