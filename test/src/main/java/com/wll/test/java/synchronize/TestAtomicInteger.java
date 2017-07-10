package com.wll.test.java.synchronize;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by wll on 17-7-10.
 */
public class TestAtomicInteger {

    public static void main(String[] args) {

        AtomicInteger count = new AtomicInteger(1);
        count.set(10);
        System.out.println(count.get());
        System.out.println(count.getAndIncrement());
        System.out.println(count.getAndDecrement());
        System.out.println(count.getAndDecrement());
    }
}
