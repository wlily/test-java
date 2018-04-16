package com.wll.test.java.ut.testng;

import org.testng.annotations.DataProvider;

public class StaticDataProvider {

    @DataProvider(name = "create")
    public static Object[][] createData() {
        return new Object[][] {
                new Object[] { new Integer(42) }
        };
    }

}
