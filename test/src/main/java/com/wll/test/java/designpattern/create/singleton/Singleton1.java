package com.wll.test.java.designpattern.create.singleton;

/**
 * 饿汉模式(线程安全)， 推荐
 *
 * 生产环境上，一般希望能尽快加载，便于尽早发现错误或者性能问题。
 * 开发和测试环境， 一般希望懒加载，以便于节省时间。
 */
public class Singleton1 {
    private static Singleton1 instance = new Singleton1();

    private Singleton1(){}

    public static Singleton1 getInstance(){
        return instance;
    }
}
