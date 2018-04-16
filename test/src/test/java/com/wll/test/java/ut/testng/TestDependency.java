package com.wll.test.java.ut.testng;

import org.testng.annotations.Test;

//Hard dependencies. All the methods you depend on must have run and succeeded for you to run.
// If at least one failure occurred in your dependencies, you will not be invoked and marked as a SKIP in the report.
// Soft dependencies. You will always be run after the methods you depend on,
// even if some of them have failed. This is useful when you just want to make sure that your test methods are run in a certain order but their success doesn't really depend on the success of others.
// A soft dependency is obtained by adding "alwaysRun=true" in your @Test annotation.
public class TestDependency {

    @Test
    public void serverStartedOk() {
        System.out.println("serverStartedOk");
    }

    @Test(dependsOnMethods = { "serverStartedOk" })
    public void method1() {
        System.out.println("method1");
    }

    @Test(groups = { "init" })
    public void serverStartedOk2()
    {
        System.out.println("serverStartedOk2");
    }

    @Test(groups = { "init" })
    public void initEnvironment() {
        System.out.println("initEnvironment");
    }

    @Test(dependsOnGroups = { "init.*" })
    public void method2() {
        System.out.println("method2");
    }

    @Test(groups = {"a"})
    public void testa(){
        System.out.println("testa");
    }
    @Test(groups = {"b"})
    public void testb(){
        System.out.println("testb");
    }
    @Test(groups = {"c"})
    public void testc(){
        System.out.println("testc");
    }
    @Test(groups = {"z"})
    public void testz(){
        System.out.println("testz");
    }
}
