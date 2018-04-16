package com.wll.test.java.ut.testng;

import org.testng.annotations.Test;

//java org.testng.TestNG -suitethreadpoolsize 3 testng1.xml testng2.xml testng3.xml
public class TestParallel {
    @Test
    public void test1(){
        System.out.println(Thread.currentThread().getName() + " is running test1");
    }

    @Test
    public void test2(){
        System.out.println(Thread.currentThread().getName() + " is running test2");
    }

    @Test
    public void test3(){
        System.out.println(Thread.currentThread().getName() + " is running test3");
    }

    @Test
    public void test4(){
        System.out.println(Thread.currentThread().getName() + " is running test4");
    }

}
