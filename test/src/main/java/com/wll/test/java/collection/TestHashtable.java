package com.wll.test.java.collection;

import java.util.*;

/**
 * Created by wll on 17-7-10.
 */
public class TestHashtable extends TestBase{

    public static void main(String[] args) {
        Hashtable hashTable = new Hashtable<String, String>();

        String[] s = new String[]{"1", "a", "dd", "22", "33", "1", "a"};

        Runnable t = () -> {
            for(int i=0; i<s.length; i++) {
                hashTable.put(s[i], Thread.currentThread().getName() + "_" + i);
            }
        };

        Thread t1 = new Thread(t);
        Thread t2 = new Thread(t);
        Thread t3 = new Thread(t);
        Thread t4 = new Thread(t);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        try{
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        new TestHashtable().walk(hashTable);
    }
}
