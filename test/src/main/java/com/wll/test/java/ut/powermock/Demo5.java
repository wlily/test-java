package com.wll.test.java.ut.powermock;

public class Demo5 {

    public boolean callPrivateMethod() {
        return isAlive();
    }

    private boolean isAlive() {
        return false;
    }

}
