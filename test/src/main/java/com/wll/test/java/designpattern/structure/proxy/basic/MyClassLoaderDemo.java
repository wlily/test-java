package com.wll.test.java.designpattern.structure.proxy.basic;

import java.io.*;
import java.lang.reflect.InvocationTargetException;

public class MyClassLoaderDemo {

    public static void main(String[] args) throws IOException {
        try {
            // 读取本地的class文件内的字节码，转换成字节码数组
            String path = MyClassLoaderDemo.class.getClassLoader().getResource("").getPath();

            InputStream inputStream = new FileInputStream( path + File.separator + "com\\wll\\test\\java\\proxy\\basic\\MyDynamicClassByASM.class");
            byte[] result = new byte[1024];

            int count = inputStream.read(result);
            // 使用自定义的类加载器将 byte字节码数组转换为对应的class对象
            MyClassLoader loader = new MyClassLoader();
            Class clazz = loader.defineMyClass( result, 0, count);
            //测试加载是否成功，打印class 对象的名称
            System.out.println(clazz.getCanonicalName());

            //实例化一个Programmer对象
            Object o= clazz.newInstance();
            //调用Programmer的code方法
            clazz.getMethod("code", null).invoke(o, null);
        } catch (IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException
                | IllegalAccessException | InstantiationException
                | IOException e) {
            e.printStackTrace();
        }
    }
}
