package com.wll.test.java.ut.testng;

import org.testng.annotations.*;

import java.lang.reflect.Method;

public class TestParameters {

    @Parameters({"datasource", "jdbcDriver"})
    @BeforeMethod
    public void beforeTest(String ds, String driver){
        System.out.println("before test");
        System.out.println("datasource: " + ds);
        System.out.println("jdbcdirver: " + driver);
    }

    @Parameters({"first-name"})
    @Test
    public void testSingleString(String firstName){
        System.out.println("Invoked testString " + firstName);
        assert "Cedric".equals(firstName);
    }

    @Parameters("db")
    @Test
    public void testNonExistentParameter(@Optional("mysql") String db){
        System.out.println("db type: " + db);
    }

    //This method will provide data to any test method that declares that its Data Provider
    //is named "test1"
    @DataProvider(name = "test1")
    public Object[][] createData1(){
        return new Object[][]{
                {"Cedric", new Integer(36)},
                {"Anne", new Integer(37)}
        };
    }

    //This test method declares that its data should be supplied by the Data Provider
    //named "test1"
    @Test(dataProvider = "test1")
    public void verifyData1(String name, Integer age){
        System.out.println(name + " " + age);
    }

//    By default, the data provider will be looked for in the current test class or one of its base classes.
// If you want to put your data provider in a different class, it needs to be a static method or a class with a non-arg constructor,
// and you specify the class where it can be found in the dataProviderClass attribute:

    @Test(dataProvider = "create", dataProviderClass = StaticDataProvider.class)
    public void testParameterFromAnotherClass(Integer n) {
        System.out.println("para is " + n);
    }

    @DataProvider(name = "dp")
    public Object[][] createData(Method m) {
        System.out.println(m.getName());  // print test method name
        return new Object[][] { new Object[] { "Cedric" }};
    }

    @Test(dataProvider = "dp")
    public void test1(String s) {
    }

    @Test(dataProvider = "dp")
    public void test2(String s) {
    }

}
