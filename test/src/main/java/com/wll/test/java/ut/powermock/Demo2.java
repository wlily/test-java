package com.wll.test.java.ut.powermock;

import java.io.File;

public class Demo2 {

    public boolean callArgumentInstance(String path) {
        File file = new File(path);
        return file.exists();
    }

}
