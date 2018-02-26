package com.wll.test.java.designpattern.create.singleton;

/**
 * 静态内部类模式（线程安全）
 * 第一次加载Singleton类不会加载SingletonHolder类，但是调用getSingleton时，才会加载SingletonHolder，才会初始化singleton。
 * 即确保了线程安全，又保证了单例对象的唯一性，延迟了单例的实例化。
 */
public class Singleton4 {
    private Singleton4(){}

    public static Singleton4 getInstance(){
        return SingletonHolder.singleton;
    }

    private static class SingletonHolder{
        private final static Singleton4 singleton = new Singleton4();
    }
}
