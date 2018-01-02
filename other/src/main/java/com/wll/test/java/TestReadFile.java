package com.wll.test.java;

import java.io.*;

/**
 * Created by wll on 12/31/17.
 */
public class TestReadFile {

    private TestReadFile(){}

    public static TestReadFile getInstance(){
        return new TestReadFile();
    }

    public void read(File file){
        try {
            read(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void read(InputStream file){
        read(new InputStreamReader(file));
    }

    public void read(Reader file){
        try {
            int len = file.read();
            System.out.println(len);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(file != null){
                try {
                    file.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
