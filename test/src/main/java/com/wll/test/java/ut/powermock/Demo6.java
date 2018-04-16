package com.wll.test.java.ut.powermock;

public class Demo6 {

    public String callSystemStaticMethod(String str) {
        return System.getProperty(str);
    }

}
