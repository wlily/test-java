package com.wll.test.java.designpattern.create.singleton;

/**
 * 枚举模式（线程安全）
 * 第一次加载Singleton类不会加载SingletonHolder类，但是调用getSingleton时，才会加载SingletonHolder，才会初始化singleton。
 * 即确保了线程安全，又保证了单例对象的唯一性，延迟了单例的实例化。
 */
public enum Singleton5{
    singleton;

    public void test(){
        System.out.print("test");
    }


    public static void main(String[] args) {
        Singleton5.singleton.test();
    }
}