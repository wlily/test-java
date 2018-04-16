package com.wll.test.java.ut.testng;

import org.testng.annotations.Test;

//后面所有的method都属于all-test
@Test(groups = {"all-test"})
public class TestGroups {

    @Test(groups = {"functest", "checkintest"})
    public void testMethod1(){
        System.out.println("test testMethod1");
    }

    @Test(groups = {"functest", "checkintest"})
    public void testMethod2(){
        System.out.println("test testMethod2");
    }

    @Test(groups = {"functest"})
    public void testMethod3(){
        System.out.println("test testMethod3");
    }

    @Test(groups = {"windows.checkintest"})
    public void testWindowsOnly(){
        System.out.println("test testWindowsOnly");
    }

    @Test(groups = {"linux.checkintest"})
    public void testLinuxOnly(){
        System.out.println("test testLinuxOnly");
    }

    @Test(groups = {"windows.functest"})
    public void testWindowsToo(){
        System.out.println("test testWindowsToo");
    }

    @Test
    public void enabledTestMethod1(){
        System.out.println("test enabledTestMethod1");
    }

    @Test
    public void enabledTestMethod2(){
        System.out.println("test enabledTestMethod2");
    }

    @Test
    public void brokenTestMethod1(){
        System.out.println("test brokenTestMethod1");
    }

    @Test
    public void brokenTestMethod2() {
        System.out.println("test brokenTestMethod2");
    }
}
