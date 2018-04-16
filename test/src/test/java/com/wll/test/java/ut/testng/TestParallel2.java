package com.wll.test.java.ut.testng;

import org.testng.annotations.Test;

//java org.testng.TestNG -suitethreadpoolsize 3 testng1.xml testng2.xml testng3.xml
public class TestParallel2 {
    @Test
    public void test1(){
        System.out.println(Thread.currentThread().getName() + " is running test21");
    }

    @Test
    public void test2(){
        System.out.println(Thread.currentThread().getName() + " is running test22");
    }

    @Test
    public void test3(){
        System.out.println(Thread.currentThread().getName() + " is running test23");
    }

    @Test
    public void test4(){
        System.out.println(Thread.currentThread().getName() + " is running test24");
    }

    @Test(threadPoolSize = 3, invocationCount = 20,  timeOut = 5000)
    public void testServer() {
        try {
            Thread.sleep(15*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " is running testServer");
    }
    }
