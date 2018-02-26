package com.wll.test.java.basic;

/**
 * //类的加载顺序：
 //        如果类还没有被加载：
 //        1、先执行父类的静态代码块和静态变量初始化，并且静态代码块和静态变量的执行顺序只跟代码中出现的顺序有关。
 //        2、执行子类的静态代码块和静态变量初始化。
 //        3、执行父类的实例变量初始化
 //        4、执行父类的构造函数
 //        5、执行子类的实例变量初始化
 //        6、执行子类的构造函数
 //
 //        如果类已经被加载：
 //        则静态代码块和静态变量就不用重复执行，再创建类对象时，只执行与实例相关的变量初始化和构造方法。
 */
public class TestLoadSequence {

    public static void main(String[] args) {
//        Parent parent = new Parent();
        Child child1 = new Child();
        Child child2 = new Child();
    }
}

class Child extends Parent{
    static String c1 = "1";

    int c2 = 2;
    int c3 = 3;

    static{
        System.out.println("Child static block 1");
    }

    public Child(){
        System.out.println("Child constructor");
    }

    static {
        System.out.println("Child static block 2");
    }
}

class Parent{
    static String p1 = "1";

    int p2 = 2;
    int p3 = 3;

    static{
        System.out.println("parent static block 1");
    }

    public Parent(){
        System.out.println("parent constructor");
    }

    static {
        System.out.println("parent static block 2");
    }
}

