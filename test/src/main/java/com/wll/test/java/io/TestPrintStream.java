package com.wll.test.java.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * Created by wll on 17-7-10.
 */
public class TestPrintStream {
    public static void main(String[] args) throws FileNotFoundException {
        PrintStream print = new PrintStream(new FileOutputStream(new File("/home/wll/1-code/study/test-java"
                + File.separator + "hello2.txt")));
        print.println(true);
        print.println("sdsdfsdfsdf");
        print.printf("姓名：%s. 年龄：%d.", "haha", 20);
        print.close();
    }
}
