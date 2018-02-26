package com.wll.test.java.designpattern.structure.proxy.basic;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;

public class JavassistGenerator {

    public static void main(String[] args) throws Exception {
        ClassPool pool = ClassPool.getDefault();
        //创建类
        CtClass cc = pool.makeClass("com.wll.test.java.designpattern.structure.proxy.basic.MyDynamicClassByJavassist");
        //定义code方法
        CtMethod method = CtNewMethod.make("public void code(){}", cc);
        //插入方法代码
        method.insertBefore("System.out.println(\"dynamic class using javassist\");");
        cc.addMethod(method);
        //保存生成的字节码
        String path = JavassistGenerator.class.getResource("").getPath();
        System.out.println(path);
        cc.writeFile(path);
    }
}
