package com.wll.test.java.resource;

import com.wll.test.java.TestReadFile;

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
//            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//            Resource resources[];
//            resources = resolver.getResources("classpath*:/txt/test.txt");
//            for(Resource resource:resources){
//                System.out.println(resource.getDescription());
//                System.out.println("aaaaa");
//                testResource.read2(resource.getInputStream());
//                System.out.println("bbbbb");
//            }
            URL url = this.getClass().getClassLoader().getResource("txt/test.txt");
            System.out.println("cccccccccccccc");
            testReadFile.read(url.openStream());
            System.out.println("dddddddddddddd");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 }
