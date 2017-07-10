package com.wll.test.java.collection;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by wll on 17-7-10.
 */
public class TestVector extends TestBase{

    public static void main(String[] args) {
        Vector vector = new Vector();
        String[] s = new String[]{"1", "a", "dd", "22", "33", "1", "a"};

        Runnable t = () -> {
            for(int i=0; i<s.length; i++) {
                vector.add(s[i]);
            }
        };

        Thread t1 = new Thread(t);
        Thread t2 = new Thread(t);
        Thread t3 = new Thread(t);
        t1.start();
        t2.start();
        t3.start();
        try{
            t1.join();
            t2.join();
            t3.join();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        new TestVector().walk(vector);
    }
}
