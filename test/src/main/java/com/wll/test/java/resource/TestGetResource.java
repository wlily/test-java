package com.wll.test.java.resource;

import java.io.IOException;
import java.net.URL;

/**
 * Created by wll on 12/31/17.
 */
public class TestGetResource {

    public static void main(String[] args) {
        new TestGetResource().test();
    }

    private void test(){
        TestReadFile testReadFile = TestReadFile.getInstance();
        try {
            System.out.println(testReadFile.readRelative("txt/test.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 }
