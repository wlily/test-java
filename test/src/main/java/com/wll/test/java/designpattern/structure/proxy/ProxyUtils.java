package com.wll.test.java.designpattern.structure.proxy;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;

public class ProxyUtils {

    /**
     * 生成JDK Proxy字节码并写到硬盘中
     */
    public static void generateClassFile(Class clazz, String proxyName)
    {
        //根据类信息和提供的代理类名称，生成字节码
        byte[] classFile = ProxyGenerator.generateProxyClass(proxyName, clazz.getInterfaces());
        String paths = clazz.getResource(".").getPath();
        System.out.println(paths);
        FileOutputStream out = null;

        try {
            //保留到硬盘中
            out = new FileOutputStream(paths + proxyName + ".class");
            out.write(classFile);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 所有动态生成的Proxy的字节码都可以使用HSDB工具查看
     * java -classpath "%JAVA_HOME%/lib/sa-jdi.jar" sun.jvm.hotspot.HSD
     */


    public static void main(String[] args) {
        ProxyUtils.generateClassFile(Function12.class, "function12");
    }


}