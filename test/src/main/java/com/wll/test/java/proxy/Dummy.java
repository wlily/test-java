package com.wll.test.java.proxy;

/**
 * Created by wll on 7/12/17.
 */
public class Dummy implements DummyInterface{
    @Override
    public void fun1() {
        System.out.println("fun1 start");
        fun2();
        System.out.println("fun1 end");
    }

    @Override
    public void fun2() {
        System.out.println("-fun2 start");

        System.out.println("-fun2 end");

    }
}
