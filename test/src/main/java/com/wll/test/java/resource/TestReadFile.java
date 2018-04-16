package com.wll.test.java.resource;

import org.apache.commons.io.IOUtils;

import java.io.*;

/**
 * Created by wll on 12/31/17.
 */
public class TestReadFile {

    private TestReadFile(){}

    public static TestReadFile getInstance(){
        return new TestReadFile();
    }

    public String readAbsolute(String fileName) throws IOException {
        return IOUtils.toString(new FileReader(fileName));
    }

    public String readRelative(String fileName) throws IOException {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
        return IOUtils.toString(inputStream);
    }
}
