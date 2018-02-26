package com.wll.test.java.designpattern.create.singleton;

/**
 * 懒汉模式(线程安全)
 * Java1.5之前，synchronize效率低，耗资源
 * Java1.5之后，synchronize的性能得到了很大提升，这种方式
 *
 * 生产环境上，一般希望能尽快加载，便于尽早发现错误或者性能问题。
 * 开发和测试环境， 一般希望懒加载，以便于节省时间。
 *
 * */
public class Singleton2 {
    private static Singleton2 instance;

    private Singleton2(){}

    /**
     * 懒汉模式(线程安全)
     */
    public static synchronized Singleton2 getInstance(){
        if(instance == null){
            instance = new Singleton2();
        }
        return instance;
    }

    /**
     * 懒汉模式(非线程安全)
     */
//    public static Singleton2 getInstance(){
//        if(instance == null){
//            instance = new Singleton2();
//        }
//        return instance;
//    }

}
